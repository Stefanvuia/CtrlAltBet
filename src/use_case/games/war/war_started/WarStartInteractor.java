package use_case.games.war.war_started;

import entity.game_logic.*;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

/**
 * The WarStartInteractor class is responsible for initializing the War card game.
 * It implements the WarStartInputBoundary interface and interacts with external systems, such as the
 * CardsAPIInterface, GameDataAccessInterface, and HistoryDataAccessInterface. It communicates the results
 * to the WarStartOutputBoundary for presentation.
 */
public class WarStartInteractor implements WarStartInputBoundary{

    /** Interface for drawing cards from an external API. */
    final CardsAPIInterface cardsAPI;

    /** Interface for accessing game-related data. */
    final GameDataAccessInterface dataAccess;

    /** Interface for accessing historical game data. */
    final HistoryDataAccessInterface historyDAO;

    /** Presenter for the "Start War" action. */
    final WarStartOutputBoundary warStartPresenter;

    /**
     * Constructs a new WarStartInteractor with the specified dependencies.
     *
     * @param cardsAPI            Interface for drawing cards from an external API.
     * @param dataAccess          Interface for accessing game-related data.
     * @param historyDAO          Interface for accessing historical game data.
     * @param warStartPresenter   Presenter for the "Start War" action.
     */
    public WarStartInteractor(
            CardsAPIInterface cardsAPI,
            GameDataAccessInterface dataAccess,
            HistoryDataAccessInterface historyDAO,
            WarStartOutputBoundary warStartPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.warStartPresenter = warStartPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the "Start War" action based on the provided input data.
     *
     * @param warStartData The input data containing the username and initial bet for starting the game.
     */
    public void execute(WarStartInputData warStartData) {
        String username = warStartData.getUsername();
        int bet = warStartData.getBet();

        if (dataAccess.getFund(username) >= bet) {
            dataAccess.editFund(username, -bet);
            String deckId = cardsAPI.shuffleNew(6);
            Player player = new WarPlayer(bet, username);
            Player dealer = new WarDealer();

            WarGameInterface game = new WarGame(player, dealer, deckId);
            /*entity.cards.Card card = cardsAPI.draw(game.getDeck());
            game.addToHand(player, card);
            game.addToHand(dealer, card);*/

            game.addToHand(player, cardsAPI.draw(game.getDeck()));
            game.addToHand(dealer, cardsAPI.draw(game.getDeck()));

           if (!game.goToWar()){
               if(game.playerWins()){
                   dataAccess.editFund(username, 2*bet);
                   historyDAO.addPayout(username, "war", bet);
               } else {
                   historyDAO.addPayout(username, "war", -bet);
               }
               warStartPresenter.prepareWarIngameView(new WarStartOutputData(game));
           }
           else{
               warStartPresenter.prepareGoToWarView(new WarStartOutputData(game));
           }

        } else {
            warStartPresenter.prepareFailView("insufficient funds");
        }
    }
}
