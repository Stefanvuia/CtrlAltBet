package use_case.games.blackjack.blackjack_logic;

import api.CardsAPIObject;
import api.TestAPIObject;
import data_access.InMemoryHistoryDataAccessObject;
import entity.StandardCard;
import entity.game_logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackHitLoseTest {
    private CardsAPIInterface api = new TestAPIObject();

    private HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private BlackJackHitOutputBoundary lPresenter = new BlackJackHitOutputBoundary() {
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

    private Game lGame;

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
        interactor.execute(new BlackJackInputGameData((BlackJackGameInterface) lGame));
    }
}