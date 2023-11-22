package use_case.games.blackjack.blackjack_start;

import entity.game_logic.*;
import use_case.games.GameDataAccessInterface;
import use_case.games.CardsAPIInterface;

public class BlackJackStartInteractor implements BlackJackStartInputBoundary{
    final CardsAPIInterface cardsAPI;
    final GameDataAccessInterface dataAccess;

    final BlackJackStartOutputBoundary blackJackStartPresenter;

    public BlackJackStartInteractor(
            CardsAPIInterface cardsAPI,
            GameDataAccessInterface dataAccess,
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

            BlackJackGameInterface blackJackGameInterface = new BlackJackGame(player, dealer, deckId);
            blackJackGameInterface.addToHand(player, cardsAPI.draw(blackJackGameInterface.getDeck(), 2));
            blackJackGameInterface.addToHand(dealer, cardsAPI.draw(blackJackGameInterface.getDeck(), 2));

            blackJackStartPresenter.prepareSuccessView(new BlackJackStartOutputData(blackJackGameInterface));
        } else {
            blackJackStartPresenter.prepareFailView("insufficient funds");
        }
    }
}
