package use_case.games.baccarat;

import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.InMemoryHistoryDataAccessObject;
import use_case.InMemoryUserDataAccessObject;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class BaccaratSuccessTest {
    private final InMemoryUserDataAccessObject uDao = new InMemoryUserDataAccessObject();

    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private final String username = "cakev";

    private final Map<String, Integer> bet = new HashMap<>();

    private CardsAPIInterface cardDrawer;

    private final BaccaratOutputBoundary presenter = new BaccaratOutputBoundary() {
        @Override
        public void preparePayoutView(BaccaratOutputData baccaratOutputData) {
            int fund = baccaratOutputData.getEndFunds();
            assert fund == 9000 || fund == 1950 || fund == 2000;
            assert baccaratOutputData.getBankerHand().size() == 3;
            assert baccaratOutputData.getPlayerHand().size() >= 2;
            assertNotNull(baccaratOutputData.getMessage());
        }

        @Override
        public void prepareFailView(BaccaratOutputData baccaratOutputData) {
            fail("fail is unexpected");
        }
    };

    @BeforeEach
    void setUp() {
        bet.put("banker", 1000);
        bet.put("player", 1000);
        bet.put("tie", 1000);
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 3000);
        uDao.save(user);
        hDao.addUser("cakev");
    }


    @Test
    void playerVIIBankerVITest() {
        cardDrawer = new BaccaratTestAPIObject("JACK", "2");
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }

    @Test
    void playerVIIBankerVIITest() {
        cardDrawer = new BaccaratTestAPIObject("JACK", "3");
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }

    @Test
    void playerVIIBankerVIIITest() {
        cardDrawer = new BaccaratTestAPIObject("JACK", "4");
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }

    @Test
    void playerVIIBankerIXTest() {
        cardDrawer = new BaccaratTestAPIObject("JACK", "5");
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }

    @Test
    void playerVIIBankerZeroTest() {
        cardDrawer = new BaccaratTestAPIObject("JACK", "6");
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }

    @Test
    void playerVIBankerZeroTest() {
        cardDrawer = new BaccaratTestAPIObject("3", "JACK");
        BaccaratInputBoundary interactor = new BaccaratInteractor(cardDrawer, uDao, presenter, hDao);
        interactor.execute(new BaccaratInputData(bet, username));
    }
}
