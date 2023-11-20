package use_case.blackjack.blackjack_logic;

import entity.BlackJackPlayer;
import entity.Card;
import entity.BlackJackGameInterface;
import use_case.blackjack.CardsAPIInterface;


public class BlackJackHitInteractor implements BlackJackHitInputBoundary{
    final CardsAPIInterface cardsAPI;

    final BlackJackHitOutputBoundary blackJackHitPresenter;

    public BlackJackHitInteractor(CardsAPIInterface cardsAPI, BlackJackHitOutputBoundary blackJackHitPresenter) {
        this.cardsAPI = cardsAPI;
        this.blackJackHitPresenter = blackJackHitPresenter;
    }

    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        BlackJackGameInterface blackJackGameInterface = blackJackInputGameData.getGame();
        Card card = cardsAPI.draw(blackJackGameInterface.getDeck());
        blackJackGameInterface.addToHand(blackJackGameInterface.getPlayer(), card);
        int change = ((BlackJackPlayer) blackJackInputGameData.getGame().getPlayer()).getBet();

        if (blackJackGameInterface.sumHand(blackJackGameInterface.getPlayer()) > 21){
            // If player Busts
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(blackJackGameInterface, true, -change);
            blackJackHitPresenter.prepareLoseView(outputGameData);
        }
        else{
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(blackJackGameInterface, false, 0);
            blackJackHitPresenter.prepareContinueView(outputGameData);
        }
    }
}
