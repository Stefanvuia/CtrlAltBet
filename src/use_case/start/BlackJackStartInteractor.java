package use_case.start;

import entity.*;
import use_case.BlackJackDataAccessInterface;
import use_case.CardsAPIInterface;

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
    public void execute(BlackJackStartInputData blackJackStartData) throws IOException {
        String username = blackJackStartData.getUsername();
        int bet = blackJackStartData.getBet();

        if (dataAccess.getFund(username) >= bet) {
            dataAccess.editFund(username, -bet);
            String deckId = cardsAPI.shuffleNew(6);
            Player player = new BlackjackPlayer(bet, username);
            Player dealer = new BlackjackDealer();

            Game game = new BlackjackGame(player, dealer, deckId);
            game.addToHand(player, cardsAPI.draw(game.getDeck(), 2));
            game.addToHand(dealer, cardsAPI.draw(game.getDeck(), 2));

            blackJackStartPresenter.prepareSuccessView(new BlackJackStartOutputData(game));
        } else {
            blackJackStartPresenter.prepareFailView("insufficient funds");
        }
    }
}
