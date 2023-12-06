package use_case.games.war.war_logic;

import entity.game_logic.WarGameInterface;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

/**
 * The WarGoToWarInteractor class is responsible for handling the "Go to War" action in the War card game.
 * It implements the WarGoToWarInputBoundary interface and interacts with external systems, such as the
 * CardsAPIInterface, GameDataAccessInterface, and HistoryDataAccessInterface. It communicates the results
 * to the WarGoToWarOutputBoundary for presentation.
 */
public class WarGoToWarInteractor implements WarGoToWarInputBoundary {

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
     * Presenter for the "Go to War" action.
     */
    final WarGoToWarOutputBoundary warGoToWarPresenter;

    /**
     * Constructs a new WarGoToWarInteractor with the specified dependencies.
     *
     * @param cardsAPI            Interface for drawing cards from an external API.
     * @param dataAccess          Interface for accessing game-related data.
     * @param historyDAO          Interface for accessing historical game data.
     * @param warGoToWarPresenter Presenter for the "Go to War" action.
     */
    public WarGoToWarInteractor(CardsAPIInterface cardsAPI,
                                GameDataAccessInterface dataAccess,
                                HistoryDataAccessInterface historyDAO,
                                WarGoToWarOutputBoundary warGoToWarPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.warGoToWarPresenter = warGoToWarPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the "Go to War" action based on the provided game data.
     *
     * @param warInputGameData The input data containing the current game state and bet information.
     */
    @Override
    public void execute(WarInputGameData warInputGameData) {
        WarGameInterface game = warInputGameData.getGame();
        int bet = warInputGameData.getBet();
        String username = warInputGameData.getUser();

        if (dataAccess.getFund(username) >= bet) {
            dataAccess.editFund(username, -bet);

            bet = bet * 2;

            /*entity.cards.Card card = cardsAPI.draw(game.getDeck());
            game.addToHand(game.getPlayer(), card);
            game.addToHand(game.getDealer(), card);*/

            game.addToHand(game.getPlayer(), cardsAPI.draw(game.getDeck()));
            game.addToHand(game.getDealer(), cardsAPI.draw(game.getDeck()));

            int change = 0;
            if (!game.goToWar()) {
                if (game.playerWins()) {
                    change = bet + bet * 3 / 4;
                }
            } else {
                change = bet * 2;
            }
            historyDAO.addPayout(username, "war", change - bet);
            dataAccess.editFund(username, change);
            warGoToWarPresenter.preparePayoutView(new WarOutputGameData(game));
        } else {
            warGoToWarPresenter.prepareFailView("insufficient funds to double the wager");
        }

    }

}
