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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WarSurrenderTest {
    private CardsAPIInterface cardDrawer = new WarTestAPIObject(true, true);
    private final FileUserDataAccessObject dao = getDAO();

    private final HistoryDataAccessInterface hDao = getHistoryDAO();

    private WarGame game;

    private final WarSurrenderOutputBoundary warPresenter = new WarSurrenderOutputBoundary() {
        @Override
        public void prepareSurrenderView(WarOutputGameData outputData) {
            ArrayList<Double> array1 = new ArrayList<Double>();

            array1.add(0.0);
            array1.add(-250.0);

            assertNotNull(outputData.getGame());
            assertEquals(500, outputData.getBet());
            assertEquals("cakev", outputData.getUser());
            assertEquals(1250, dao.getFund("cakev"));
            assertEquals(array1 ,hDao.getPayouts("cakev","war"));
        }
    };

    @BeforeEach
    void setUp() {
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1500);
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
    void SurrenderTest() {
        WarInputGameData inputData = new WarInputGameData((WarGameInterface) game);
        WarSurrenderInteractor interactor = new WarSurrenderInteractor(cardDrawer, dao, hDao, warPresenter);
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
