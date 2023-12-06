package use_case.games.blackjack.blackjack_start;

import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.InMemoryUserDataAccessObject;
import use_case.games.CardsAPIInterface;
import use_case.games.blackjack.BlackJackTestAPIObject;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackStartSuccessTest {
    private final CardsAPIInterface api = new BlackJackTestAPIObject("JACk");
    private InMemoryUserDataAccessObject dao;

    private final BlackJackStartOutputBoundary successPresenter = new BlackJackStartOutputBoundary() {

        @Override
        public void prepareSuccessView(BlackJackStartOutputData outputData) {
            assertNotNull(outputData.getGame());
            assertEquals("cakev", outputData.getUser());
            assertEquals(1000, outputData.getBet());
        }

        @Override
        public void prepareFailView(String error) {
            fail("Use case failure is unexpected.");
        }
    };

    @BeforeEach
    void setUp() {
        dao = new InMemoryUserDataAccessObject();
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000);
        dao.save(user);
    }
    @Test
    void successTest() {
        BlackJackStartInputData inputData = new BlackJackStartInputData("cakev", 1000);
        BlackJackStartInputBoundary interactor = new BlackJackStartInteractor(api, dao, successPresenter);
        interactor.execute(inputData);
    }
}