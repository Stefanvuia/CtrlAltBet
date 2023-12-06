package use_case.games.blackjack.blackjack_logic;

import entity.cards.StandardCard;
import entity.game_logic.*;
import entity.user.CommonUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.InMemoryHistoryDataAccessObject;
import use_case.InMemoryUserDataAccessObject;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.blackjack.BlackJackTestAPIObject;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackStandWinTest {
    private final CardsAPIInterface api = new BlackJackTestAPIObject("JACK");
    private final InMemoryUserDataAccessObject uDao = new InMemoryUserDataAccessObject();

    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();
    private final BlackJackStandOutputBoundary presenter = new BlackJackStandOutputBoundary() {
        @Override
        public void prepareWinView(BlackJackOutputGameData outputGameData) {
            assertTrue(outputGameData.gameFinished());
            assert (outputGameData.getChange() == 1000 || outputGameData.getChange() == 1500);
            assertNotNull(outputGameData.getGame());
        }

        @Override
        public void prepareLoseView(BlackJackOutputGameData outputGameData) {
            fail("lose is unexpected");
        }

        @Override
        public void preparePushView(BlackJackOutputGameData outputGameData) {
            fail("push is unexpected");
        }
    };
    private Player player;
    private BlackJackGameInterface game;

    @BeforeEach
    void setUp() {
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000);
        uDao.save(user);
        Player dealer = new BlackJackDealer();
        player = new BlackJackPlayer(1000, "cakev");
        hDao.addUser("cakev");

        game = new BlackJackGame(
                player,
                dealer,
                api.shuffleNew(6)
        );
        game.addToHand(dealer, api.draw(""));
        game.addToHand(dealer, api.draw(""));
        game.addToHand(dealer, api.draw(""));
    }

    @Test
    void blackJackWinTest() {
        game.addToHand(player, new StandardCard("ACE", ""));
        game.addToHand(player, api.draw(""));
        BlackJackStandInputBoundary interactor = new BlackJackStandInteractor(api, uDao, presenter, hDao);
        interactor.execute(new BlackJackInputGameData(game));
    }

    @Test
    void winTest() {
        game.addToHand(player, api.draw(""));
        game.addToHand(player, api.draw(""));
        BlackJackStandInputBoundary interactor = new BlackJackStandInteractor(api, uDao, presenter, hDao);
        interactor.execute(new BlackJackInputGameData(game));
    }
}
