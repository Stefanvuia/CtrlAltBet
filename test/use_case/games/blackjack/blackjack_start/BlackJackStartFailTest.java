package use_case.games.blackjack.blackjack_start;

import use_case.games.blackjack.BlackJackTestAPIObject;
import use_case.games.InMemoryUserDataAccessObject;
import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.games.CardsAPIInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BlackJackStartFailTest {
    private final CardsAPIInterface api = new BlackJackTestAPIObject("JACK");
    private InMemoryUserDataAccessObject dao;

    private final BlackJackStartOutputBoundary failPresenter = new BlackJackStartOutputBoundary() {

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
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000);
        dao.save(user);
    }

    @Test
    void failTest() {
        BlackJackStartInputData inputData = new BlackJackStartInputData("cakev", 2000);
        BlackJackStartInputBoundary interactor = new BlackJackStartInteractor(api, dao, failPresenter);
        interactor.execute(inputData);
    }
}
