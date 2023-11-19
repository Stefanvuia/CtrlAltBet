package use_case.blackjack.blackjack_logic;

import entity.BlackJackPlayer;
import entity.Card;
import entity.Game;
import use_case.blackjack.CardsAPIInterface;

import java.io.IOException;


public class BlackJackHitInteractor implements BlackJackHitInputBoundary{
    final CardsAPIInterface cardsAPI;

    final BlackJackHitOutputBoundary blackJackHitPresenter;

    public BlackJackHitInteractor(CardsAPIInterface cardsAPI, BlackJackHitOutputBoundary blackJackHitPresenter) {
        this.cardsAPI = cardsAPI;
        this.blackJackHitPresenter = blackJackHitPresenter;
    }

    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        Game game = blackJackInputGameData.getGame();
        Card card = cardsAPI.draw(game.getDeck());
        game.addToHand(game.getPlayer(), card);
        int change = ((BlackJackPlayer) blackJackInputGameData.getGame().getPlayer()).getBet();

        if (game.sumHand(game.getPlayer()) > 21){
            // If player Busts
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(game, true, -change);
            blackJackHitPresenter.prepareLoseView(outputGameData);
        }
        else{
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(game, false, 0);
            blackJackHitPresenter.prepareContinueView(outputGameData);
        }
    }
}
