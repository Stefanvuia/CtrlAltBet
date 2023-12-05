package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackPlayer;
import entity.Card;
import entity.game_logic.BlackJackGameInterface;
import entity.game_logic.Player;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;

/**
 * Interactor class for handling the "hit" action in a Blackjack game.
 */
public class BlackJackHitInteractor implements BlackJackHitInputBoundary {

    /**
     * Interface for interacting with the Cards API.
     */
    final CardsAPIInterface cardsAPI;

    /**
     * Data access interface for handling user history data.
     */
    final HistoryDataAccessInterface historyDAO;

    /**
     * Presenter responsible for displaying Blackjack hit action output to the user.
     */
    final BlackJackHitOutputBoundary blackJackHitPresenter;

    /**
     * Constructs a BlackJackHitInteractor object with the specified dependencies.
     *
     * @param cardsAPI              The interface for interacting with the Cards API.
     * @param blackJackHitPresenter Presenter for displaying Blackjack hit action output.
     * @param historyDAO            Data access interface for handling user history data.
     */
    public BlackJackHitInteractor(CardsAPIInterface cardsAPI, BlackJackHitOutputBoundary blackJackHitPresenter, HistoryDataAccessInterface historyDAO) {
        this.cardsAPI = cardsAPI;
        this.blackJackHitPresenter = blackJackHitPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the "hit" action in a Blackjack game based on the provided game data.
     *
     * @param blackJackInputGameData The input data containing the Blackjack game state.
     */
    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        BlackJackGameInterface blackJackGameInterface = blackJackInputGameData.getGame();
        Card card = cardsAPI.draw(blackJackGameInterface.getDeck());
        blackJackGameInterface.addToHand(blackJackGameInterface.getPlayer(), card);

        Player player = blackJackInputGameData.getGame().getPlayer();
        int change = ((BlackJackPlayer) player).getBet();

        if (blackJackGameInterface.sumHand(blackJackGameInterface.getPlayer()) > 21) {
            // If player Busts
            historyDAO.addPayout(((BlackJackPlayer) player).getUsername(), "blackjack", -change);
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(blackJackGameInterface, true, -change);
            blackJackHitPresenter.prepareLoseView(outputGameData);
        } else {
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(blackJackGameInterface, false, 0);
            blackJackHitPresenter.prepareContinueView(outputGameData);
        }
    }
}
