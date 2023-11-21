package use_case.blackjack.blackjack_logic;

import entity.BlackJackPlayer;
import entity.BlackJackGameInterface;
import entity.Player;
import use_case.blackjack.BlackJackDataAccessInterface;
import use_case.blackjack.CardsAPIInterface;

public class BlackJackStandInteractor implements BlackJackStandInputBoundary{
    final CardsAPIInterface cardsAPI;
    final BlackJackDataAccessInterface dataAccessObject;
    final BlackJackStandOutputBoundary blackJackStandPresenter;

    public BlackJackStandInteractor(
            CardsAPIInterface cardsAPI,
            BlackJackDataAccessInterface dataAccessObject,
            BlackJackStandOutputBoundary blackJackStandPresenter
    ) {
        this.cardsAPI = cardsAPI;
        this.dataAccessObject = dataAccessObject;
        this.blackJackStandPresenter = blackJackStandPresenter;
    }
    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        BlackJackGameInterface blackJackGameInterface = blackJackInputGameData.getGame();
        Player dealer = blackJackGameInterface.getDealer();
        BlackJackPlayer user = (BlackJackPlayer) blackJackGameInterface.getPlayer();
        String deck = blackJackGameInterface.getDeck();
        Integer playerSum = blackJackGameInterface.sumHand(user);
        int dealerSum = blackJackGameInterface.sumHand(dealer);

        while (dealerSum < 17) {
            blackJackGameInterface.addToHand(dealer, cardsAPI.draw(deck));
            dealerSum = blackJackGameInterface.sumHand(dealer);
        }

        if (blackJackGameInterface.userWin() && Integer.valueOf(user.getHand().size()).equals(2) && playerSum.equals(21)) {
            // Player blackjack
            dataAccessObject.editFund(user.getUsername(), (int) (user.getBet() * 2.5));
            blackJackStandPresenter.prepareWinView(new BlackJackOutputGameData(blackJackGameInterface, true, (int) (user.getBet() * 1.5)));
        } else if (blackJackGameInterface.userWin()) {
            // Player win
            dataAccessObject.editFund(user.getUsername(), (user.getBet() * 2));
            blackJackStandPresenter.prepareWinView(new BlackJackOutputGameData(blackJackGameInterface, true, user.getBet()));
        } else if (playerSum.equals(dealerSum)) {
            // Push
            dataAccessObject.editFund(user.getUsername(), user.getBet());
            blackJackStandPresenter.preparePushView(new BlackJackOutputGameData(blackJackGameInterface, true, 0));
        } else {
            // Dealer win
            blackJackStandPresenter.prepareLoseView(new BlackJackOutputGameData(blackJackGameInterface, true, -user.getBet()));
        }
    }
}
