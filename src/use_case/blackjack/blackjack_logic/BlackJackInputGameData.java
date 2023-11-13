package use_case.blackjack.blackjack_logic;

import entity.BlackJackGame;
import entity.Game;

public class BlackJackInputGameData {
    private Game game;

    public BlackJackInputGameData(Game game){
        this.game = game;
    }

    BlackJackGame getGame(){ return (BlackJackGame) this.game; }
}
