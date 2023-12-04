package use_case.games.blackjack.blackjack_logic;

import api.TestAPIObject;
import data_access.InMemoryHistoryDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.game_logic.*;
import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackPushTest {
    private CardsAPIInterface api = new TestAPIObject();
    private InMemoryUserDataAccessObject uDao = new InMemoryUserDataAccessObject();

    private HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();
    private User user;

    private Player player;

    private Player dealer;
    private Game game;

    private BlackJackStandOutputBoundary presenter = new BlackJackStandOutputBoundary() {
        @Override
        public void prepareWinView(BlackJackOutputGameData outputGameData) {
            fail("win is unexpected");
        }

        @Override
        public void prepareLoseView(BlackJackOutputGameData outputGameData) {
            fail("lose is unexpected");
        }

        @Override
        public void preparePushView(BlackJackOutputGameData outputGameData) {
            assertTrue(outputGameData.gameFinished());
            assertNotNull(outputGameData.getGame());
            assertEquals(0, outputGameData.getChange());
        }
    };

    @BeforeEach
    void setUp() {
        user = new CommonUser("cakev","qwerty", LocalDateTime.now(), 1000);
        uDao.save(user);
        dealer = new BlackJackDealer();
        player = new BlackJackPlayer(1000, "cakev");
        hDao.addUser("cakev");

        game = new BlackJackGame(
                player,
                dealer,
                api.shuffleNew(6)
        );
        game.addToHand(dealer, api.draw(""));
        game.addToHand(player, api.draw(""));
        game.addToHand(player, api.draw(""));
    }

    @Test
    void pushTest() {
        BlackJackStandInputBoundary interactor = new BlackJackStandInteractor(api, uDao, presenter, hDao);
        interactor.execute(new BlackJackInputGameData((BlackJackGameInterface) game));
    }
}
