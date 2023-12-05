package use_case.games.blackjack.blackjack_start;

import entity.game_logic.*;
import use_case.games.GameDataAccessInterface;
import use_case.games.CardsAPIInterface;

/**
 * Interactor class for initiating a Blackjack game.
 */
public class BlackJackStartInteractor implements BlackJackStartInputBoundary {

    /**
     * Interface for interacting with the Cards API.
     */
    final CardsAPIInterface cardsAPI;

    /**
     * Data access interface for handling game-related data.
     */
    final GameDataAccessInterface dataAccess;

    /**
     * Presenter responsible for displaying the result of initiating the Blackjack game.
     */
    final BlackJackStartOutputBoundary blackJackStartPresenter;

    /**
     * Constructs a BlackJackStartInteractor object with the specified dependencies.
     *
     * @param cardsAPI               The interface for interacting with the Cards API.
     * @param dataAccess             Data access interface for handling game-related data.
     * @param blackJackStartPresenter Presenter for displaying the result of initiating the Blackjack game.
     */
    public BlackJackStartInteractor(
            CardsAPIInterface cardsAPI,
            GameDataAccessInterface dataAccess,
            BlackJackStartOutputBoundary blackJackStartPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.blackJackStartPresenter = blackJackStartPresenter;
    }

    /**
     * Executes the process of initiating a Blackjack game based on the provided input data.
     *
     * @param blackJackStartData The input data containing the player's username and bet amount.
     */
    @Override
    public void execute(BlackJackStartInputData blackJackStartData) {
        String username = blackJackStartData.getUsername();
        int bet = blackJackStartData.getBet();

        if (dataAccess.getFund(username) >= bet) {
            dataAccess.editFund(username, -bet);
            String deckId = cardsAPI.shuffleNew(6);
            Player player = new BlackJackPlayer(bet, username);
            Player dealer = new BlackJackDealer();

            BlackJackGameInterface blackJackGameInterface = new BlackJackGame(player, dealer, deckId);
            blackJackGameInterface.addToHand(player, cardsAPI.draw(blackJackGameInterface.getDeck(), 2));
            blackJackGameInterface.addToHand(dealer, cardsAPI.draw(blackJackGameInterface.getDeck(), 2));

            blackJackStartPresenter.prepareSuccessView(new BlackJackStartOutputData(blackJackGameInterface));
        } else {
            blackJackStartPresenter.prepareFailView("insufficient funds");
        }
    }
}
