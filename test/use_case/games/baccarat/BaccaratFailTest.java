package use_case.games.baccarat;

import use_case.InMemoryHistoryDataAccessObject;
import use_case.InMemoryUserDataAccessObject;
import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BaccaratFailTest {
    private final InMemoryUserDataAccessObject uDao = new InMemoryUserDataAccessObject();

    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private final String username = "cakev";

    private Map<String, Integer> bet = new HashMap<>();

    private CardsAPIInterface cardDrawer = new BaccaratTestAPIObject("ACE", "ACE");

    private final BaccaratOutputBoundary presenter = new BaccaratOutputBoundary() {
        @Override
        public void preparePayoutView(BaccaratOutputData baccaratOutputData) {
            fail("success in unexpected");
        }

        @Override
        public void prepareFailView(BaccaratOutputData baccaratOutputData) {
            assertEquals("Insufficient funds!", baccaratOutputData.getMessage());
            assertEquals(2999, baccaratOutputData.getEndFunds());
        }
    };

    @BeforeEach
    void setUp() {
        bet.put("banker", 1000);
        bet.put("player", 1000);
        bet.put("tie", 1000);
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 2999);
        uDao.save(user);
        hDao.addUser("cakev");
    }

    @Test
    void failTest() {
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }
}
