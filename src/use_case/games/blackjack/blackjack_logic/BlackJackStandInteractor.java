package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackPlayer;
import entity.game_logic.BlackJackGameInterface;
import entity.game_logic.Player;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.GameDataAccessInterface;
import use_case.games.CardsAPIInterface;

/**
 * Interactor class for handling the "stand" action in a Blackjack game.
 */
public class BlackJackStandInteractor implements BlackJackStandInputBoundary {

    /**
     * Interface for interacting with the Cards API.
     */
    final CardsAPIInterface cardsAPI;

    /**
     * Data access interface for handling game-related data.
     */
    final GameDataAccessInterface dataAccessObject;

    /**
     * Presenter responsible for displaying Blackjack stand action output to the user.
     */
    final BlackJackStandOutputBoundary blackJackStandPresenter;

    /**
     * Data access interface for handling user history data.
     */
    final HistoryDataAccessInterface historyDAO;

    /**
     * Constructs a BlackJackStandInteractor object with the specified dependencies.
     *
     * @param cardsAPI                The interface for interacting with the Cards API.
     * @param dataAccessObject        Data access interface for handling game-related data.
     * @param blackJackStandPresenter Presenter for displaying Blackjack stand action output.
     * @param historyDAO              Data access interface for handling user history data.
     */
    public BlackJackStandInteractor(
            CardsAPIInterface cardsAPI,
            GameDataAccessInterface dataAccessObject,
            BlackJackStandOutputBoundary blackJackStandPresenter,
            HistoryDataAccessInterface historyDAO
    ) {
        this.cardsAPI = cardsAPI;
        this.dataAccessObject = dataAccessObject;
        this.blackJackStandPresenter = blackJackStandPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the "stand" action in a Blackjack game based on the provided game data.
     *
     * @param blackJackInputGameData The input data containing the Blackjack game state.
     */
    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        // ... (Method logic is already commented in the code)
    }
}
