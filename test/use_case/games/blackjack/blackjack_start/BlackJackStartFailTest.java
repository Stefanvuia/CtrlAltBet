package use_case.games.blackjack.blackjack_start;

import api.CardsAPIObject;
import api.TestAPIObject;
import data_access.InMemoryUserDataAccessObject;
import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.games.CardsAPIInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BlackJackStartFailTest {
    private CardsAPIInterface api = new TestAPIObject();
    private InMemoryUserDataAccessObject dao;
    private User user;
    private BlackJackStartInputData inputData;

    private BlackJackStartOutputBoundary failPresenter = new BlackJackStartOutputBoundary() {

        @Override
        public void prepareSuccessView(BlackJackStartOutputData outputData) {
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
        user = new CommonUser("cakev","qwerty", LocalDateTime.now(), 1000);
        dao.save(user);
    }

    @Test
    void failTest() {
        inputData = new BlackJackStartInputData("cakev", 2000);
        BlackJackStartInputBoundary interactor = new BlackJackStartInteractor(api, dao, failPresenter);
        interactor.execute(inputData);
    }
}
