package use_case.games.war.war_start;

import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.InMemoryHistoryDataAccessObject;
import use_case.InMemoryUserDataAccessObject;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.war.WarTestAPIObject;
import use_case.games.war.war_started.WarStartInputData;
import use_case.games.war.war_started.WarStartInteractor;
import use_case.games.war.war_started.WarStartOutputBoundary;
import use_case.games.war.war_started.WarStartOutputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class WarStartGoToWarTest {
    private final CardsAPIInterface api = new WarTestAPIObject(true,true);
    private InMemoryUserDataAccessObject dao;
    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private final WarStartOutputBoundary warPresenter = new WarStartOutputBoundary() {

        @Override
        public void prepareWarIngameView(WarStartOutputData outputData) {
            fail("Use case success is unexpected.");
        }
        @Override
        public void prepareGoToWarView(WarStartOutputData outputData) {
            assertNotNull(outputData.getGame());
            assertEquals("cakev", outputData.getUser());
            assertEquals(500, outputData.getBet());
        }

        @Override
        public void prepareFailView(String error) {
            fail("Use case success is unexpected.");
        }
    };

    @BeforeEach
    void setUp() {
        dao = new InMemoryUserDataAccessObject();
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000);
        dao.save(user);
    }

    @Test
    void goToWarTest() {
        WarStartInputData inputData = new WarStartInputData("cakev", 500);
        WarStartInteractor interactor = new WarStartInteractor(api, dao, hDao, warPresenter);
        interactor.execute(inputData);
    }
}
