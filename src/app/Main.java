package app;

import api.CardsAPIObject;
import entity.*;
import use_case.BlackJackHitInteractor;
import use_case.BlackJackInputGameData;
import use_case.CardsAPIInterface;

import java.io.IOException;

public class Main {
    static CardsAPIInterface a = new CardsAPIObject();
    public static void main(String[] args) throws IOException {
        String b = a.shuffleNew(5);
        Player user = new BlackjackPlayer(100, "stefan", a.draw(b, 2));
        Player dealer = new BlackjackDealer(a.draw(b, 2));
        Game game = new BlackjackGame(user, dealer, b);

        System.out.println(game.sumHand(game.getPlayer()) + " is the current deck value - press any key to hit");
        System.in.read();
        BlackJackInputGameData gameData = new BlackJackInputGameData(game);
        BlackJackHitInteractor hitInteractor = new BlackJackHitInteractor();
        hitInteractor.execute(gameData);


    }
}
