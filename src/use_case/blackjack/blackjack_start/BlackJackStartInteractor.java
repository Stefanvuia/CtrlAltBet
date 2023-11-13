package use_case.blackjack.blackjack_start;

import entity.*;
import use_case.blackjack.BlackJackDataAccessInterface;
import use_case.blackjack.CardsAPIInterface;

import java.io.IOException;

public class BlackJackStartInteractor implements BlackJackStartInputBoundary{
    final CardsAPIInterface cardsAPI;
    final BlackJackDataAccessInterface dataAccess;

    final BlackJackStartOutputBoundary blackJackStartPresenter;

    public BlackJackStartInteractor(
            CardsAPIInterface cardsAPI,
            BlackJackDataAccessInterface dataAccess,
            BlackJackStartOutputBoundary blackJackStartPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.blackJackStartPresenter = blackJackStartPresenter;
    }
    @Override
    public void execute(BlackJackStartInputData blackJackStartData) {
        String username = blackJackStartData.getUsername();
        int bet = blackJackStartData.getBet();

        if (dataAccess.getFund(username) >= bet) {
            dataAccess.editFund(username, -bet);
            String deckId = cardsAPI.shuffleNew(6);
            Player player = new BlackJackPlayer(bet, username);
            Player dealer = new BlackJackDealer();

            Game game = new BlackJackGame(player, dealer, deckId);
            game.addToHand(player, cardsAPI.draw(game.getDeck(), 2));
            game.addToHand(dealer, cardsAPI.draw(game.getDeck(), 2));

            blackJackStartPresenter.prepareSuccessView(new BlackJackStartOutputData(game));
        } else {
            blackJackStartPresenter.prepareFailView("insufficient funds");
        }
    }
}
