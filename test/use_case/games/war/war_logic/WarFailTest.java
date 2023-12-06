package use_case.games.war.war_logic;

import data_access.FileUserDataAccessObject;
import data_access.HistoryDataAccessObject;
import entity.game_logic.*;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.war.WarTestAPIObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;

public class WarFailTest {
    private CardsAPIInterface cardDrawer = new WarTestAPIObject(true, true);
    private final FileUserDataAccessObject dao = getDAO();

    private final HistoryDataAccessInterface hDao = getHistoryDAO();

    private WarGame game;

    private final WarGoToWarOutputBoundary warPresenter = new WarGoToWarOutputBoundary() {

        @Override
        public void preparePayoutView(WarOutputGameData outputData) {
            fail("Use case success is unexpected.");

        }
        @Override
        public void prepareFailView(String error) {
            assertEquals("insufficient funds to double the wager", error);
        }
    };

    @BeforeEach
    void setUp() {
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 750);
        dao.save(user);

        Player player = new WarPlayer(500, "cakev");
        game = new WarGame(
                player,
                new WarDealer(),
                cardDrawer.shuffleNew(6)
        );
        game.addToHand(player, cardDrawer.draw(game.getDeck()));
        game.addToHand(game.getDealer(), cardDrawer.draw(game.getDeck()));

        dao.editFund("cakev",-500);

    }
    @AfterEach
    void deleteFiles() {
        File file1 = new File("testusers.csv");
        File file2 = new File("./testhistory.csv");
        file1.delete();
        file2.delete();

    }

    @Test
    void GoToWarFailTest() {
        cardDrawer = new WarTestAPIObject(false, true);
        WarInputGameData inputData = new WarInputGameData((WarGameInterface) game);
        WarGoToWarInteractor interactor = new WarGoToWarInteractor(cardDrawer, dao, hDao, warPresenter);
        interactor.execute(inputData);
    }

    private static FileUserDataAccessObject getDAO() {
        FileUserDataAccessObject fileUserDataAccessObject;
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("testusers.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return fileUserDataAccessObject;
    }

    public static HistoryDataAccessObject getHistoryDAO() {
        HistoryDataAccessObject historyDataAccessObject;
        try {
            historyDataAccessObject = new HistoryDataAccessObject("./testhistory.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return historyDataAccessObject;
    }
}
