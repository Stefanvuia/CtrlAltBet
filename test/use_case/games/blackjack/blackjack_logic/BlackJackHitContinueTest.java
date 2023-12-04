package use_case.games.blackjack.blackjack_logic;

import use_case.games.blackjack.BlackJackTestAPIObject;
import use_case.games.InMemoryHistoryDataAccessObject;
import entity.game_logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackHitContinueTest {
    private final CardsAPIInterface api = new BlackJackTestAPIObject("JACK");

    private final HistoryDataAccessInterface hDao = new InMemoryHistoryDataAccessObject();

    private final BlackJackHitOutputBoundary cPresenter = new BlackJackHitOutputBoundary() {
        @Override
        public void prepareContinueView(BlackJackOutputGameData outputGameData) {
            assertFalse(outputGameData.gameFinished());
            assertEquals(0, outputGameData.getChange());
            assertNotNull(outputGameData.getGame());
        }

        @Override
        public void prepareLoseView(BlackJackOutputGameData outputGameData) {
            fail("lose is unexpected");
        }
    };

    private BlackJackGameInterface cGame;

    @BeforeEach
    void setUp() {
        Player cPlayer = new BlackJackPlayer(1000, "cakev");
        hDao.addUser("cakev");

        cGame = new BlackJackGame(
                cPlayer,
                new BlackJackDealer(),
                api.shuffleNew(6)
        );
    }

    @Test
    void continueTest() {
        BlackJackHitInputBoundary interactor = new BlackJackHitInteractor(api, cPresenter, hDao);
        interactor.execute(new BlackJackInputGameData(cGame));
    }
}
