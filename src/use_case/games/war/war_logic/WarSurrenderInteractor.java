package use_case.games.war.war_logic;

import entity.game_logic.WarGameInterface;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

/**
 * The WarSurrenderInteractor class is responsible for handling the "Surrender" action in the War card game.
 * It implements the WarSurrenderInputBoundary interface and interacts with external systems, such as the
 * CardsAPIInterface, GameDataAccessInterface, and HistoryDataAccessInterface. It communicates the results
 * to the WarSurrenderOutputBoundary for presentation.
 */
public class WarSurrenderInteractor implements WarSurrenderInputBoundary {

    /**
     * Interface for drawing cards from an external API.
     */
    final CardsAPIInterface cardsAPI;

    /**
     * Interface for accessing game-related data.
     */
    final GameDataAccessInterface dataAccess;

    /**
     * Interface for accessing historical game data.
     */
    final HistoryDataAccessInterface historyDAO;

    /**
     * Presenter for the "Surrender" action.
     */
    final WarSurrenderOutputBoundary warSurrenderPresenter;

    /**
     * Constructs a new WarSurrenderInteractor with the specified dependencies.
     *
     * @param cardsAPI              Interface for drawing cards from an external API.
     * @param dataAccess            Interface for accessing game-related data.
     * @param historyDAO            Interface for accessing historical game data.
     * @param warSurrenderPresenter Presenter for the "Surrender" action.
     */
    public WarSurrenderInteractor(CardsAPIInterface cardsAPI,
                                  GameDataAccessInterface dataAccess,
                                  HistoryDataAccessInterface historyDAO,
                                  WarSurrenderOutputBoundary warSurrenderPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.warSurrenderPresenter = warSurrenderPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the "Surrender" action based on the provided game data.
     *
     * @param warInputGameData The input data containing the current game state and bet information.
     */
    @Override
    public void execute(WarInputGameData warInputGameData) {
        WarGameInterface game = warInputGameData.getGame();
        int bet = warInputGameData.getBet();
        String username = warInputGameData.getUser();

        dataAccess.editFund(username, bet / 2);
        historyDAO.addPayout(username, "war", -((double) bet / 2));
        warSurrenderPresenter.prepareSurrenderView(new WarOutputGameData(game));
    }
}
