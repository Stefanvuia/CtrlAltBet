package use_case.blackjack.blackjack_logic;

import entity.BlackJackPlayer;
import entity.Game;
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
        Game game = blackJackInputGameData.getGame();
        Player dealer = game.getDealer();
        BlackJackPlayer user = (BlackJackPlayer) game.getPlayer();
        String deck = game.getDeck();
        Integer playerSum = game.sumHand(user);
        int dealerSum = game.sumHand(dealer);
        int change;

        while (dealerSum < 17) {
            game.addToHand(dealer, cardsAPI.draw(deck));
            dealerSum = game.sumHand(dealer);
        }

        if (game.userWin() && Integer.valueOf(user.getHand().size()).equals(2) && playerSum.equals(21)) {
            // Player blackjack
            dataAccessObject.editFund(user.getUsername(), (int) (user.getBet() * 2.5));
            blackJackStandPresenter.prepareWinView(new BlackJackOutputGameData(game, true, (int) (user.getBet() * 1.5)));
        } else if (game.userWin()) {
            // Player win
            dataAccessObject.editFund(user.getUsername(), (user.getBet() * 2));
            blackJackStandPresenter.prepareWinView(new BlackJackOutputGameData(game, true, user.getBet()));
        } else if (playerSum.equals(dealerSum)) {
            // Push
            dataAccessObject.editFund(user.getUsername(), user.getBet());
            blackJackStandPresenter.preparePushView(new BlackJackOutputGameData(game, true, 0));
        } else {
            // Dealer win
            blackJackStandPresenter.prepareLoseView(new BlackJackOutputGameData(game, true, -user.getBet()));
        }
    }
}
