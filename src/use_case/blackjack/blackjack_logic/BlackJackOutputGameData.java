package use_case.blackjack.blackjack_logic;

import entity.Game;

public class BlackJackOutputGameData {
    private Game game;
    private boolean gameFinished;

    public BlackJackOutputGameData(Game game, boolean gameFinished){
        this.game = game;
        this.gameFinished = gameFinished;
    }

    public boolean gameFinished(){return this.gameFinished;}

    public Game getGame(){return this.game;}


}
