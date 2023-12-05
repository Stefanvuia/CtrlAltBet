package use_case.games.blackjack.blackjack_logic;

import use_case.games.blackjack.BlackJackTestAPIObject;
import use_case.InMemoryHistoryDataAccessObject;
import entity.StandardCard;
import entity.game_logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackHitLoseTest {
    private final CardsAPIInterface api = new BlackJackTestAPIObject("JACK");

    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private final BlackJackHitOutputBoundary lPresenter = new BlackJackHitOutputBoundary() {
        @Override
        public void prepareContinueView(BlackJackOutputGameData outputGameData) {
            fail("continue is unexpected");
        }

        @Override
        public void prepareLoseView(BlackJackOutputGameData outputGameData) {
            assertTrue(outputGameData.gameFinished());
            assertNotNull(outputGameData.getGame());
            assertEquals(-1000, outputGameData.getChange());
        }
    };

    private BlackJackGameInterface lGame;

    @BeforeEach
    void setUp() {
        Player lPlayer = new BlackJackPlayer(1000, "cakev");
        hDao.addUser("cakev");

        lGame = new BlackJackGame(
                lPlayer,
                new BlackJackDealer(),
                api.shuffleNew(6)
        );
        lGame.addToHand(lPlayer, api.draw(""));
        lGame.addToHand(lPlayer, api.draw(""));
        lGame.addToHand(lPlayer, new StandardCard("ACE", ""));
    }
    @Test
    void loseTest() {
        BlackJackHitInputBoundary interactor = new BlackJackHitInteractor(api, lPresenter, hDao);
        interactor.execute(new BlackJackInputGameData(lGame));
    }
}