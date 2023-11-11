package use_case;

import api.CardsAPIObject;
import entity.Game;

import java.io.IOException;


public class BlackJackHitInteractor implements BlackJackHitInputBoundary{
    CardsAPIInterface cardsA = new CardsAPIObject();

    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) throws IOException {
        Game game = blackJackInputGameData.getGame();
        game.addToHand(game.getPlayer(), cardsA.draw(game.getDeck()));
        if (game.sumHand(game.getPlayer()) > 21){
            // If player Busts
            System.out.println("bust: "+ game.sumHand(game.getPlayer()));
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(game, true);
        }
        else{
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(game, true);
            System.out.println(game.sumHand(game.getPlayer())+ " is the current value - Hit again");
            System.in.read();

            execute(new BlackJackInputGameData(outputGameData.getGame()));

        }
    }
}
