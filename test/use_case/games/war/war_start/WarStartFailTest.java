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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WarStartFailTest {
    private final CardsAPIInterface api = new WarTestAPIObject(false,false);
    private InMemoryUserDataAccessObject dao;
    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private final WarStartOutputBoundary failPresenter = new WarStartOutputBoundary() {

        @Override
        public void prepareWarIngameView(WarStartOutputData outputData) {
            fail("Use case success is unexpected.");
        }
        @Override
        public void prepareGoToWarView(WarStartOutputData outputData) {
            fail("Use case success is unexpected.");
        }

        @Override
        public void prepareFailView(String error) {
            assertEquals("insufficient funds", error);
        }
    };

    @BeforeEach
    void setUp() {
        dao = new InMemoryUserDataAccessObject();
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000);
        dao.save(user);
    }

    @Test
    void failTest() {
        WarStartInputData inputData = new WarStartInputData("cakev", 2000);
        WarStartInteractor interactor = new WarStartInteractor(api, dao, hDao, failPresenter);
        interactor.execute(inputData);
    }
}
