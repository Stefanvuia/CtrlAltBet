package use_case;

import entity.BlackjackGame;
import entity.Game;
import entity.Player;

public class BlackJackInputGameData {
    private Game game;

    public BlackJackInputGameData(Game game){
        this.game = game;
    }

    BlackjackGame getGame(){ return (BlackjackGame) this.game; }
}
