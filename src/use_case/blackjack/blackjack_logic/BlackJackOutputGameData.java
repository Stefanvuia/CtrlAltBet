package use_case.blackjack.blackjack_logic;

import entity.Game;

public class BlackJackOutputGameData {
    private Game game;
    private boolean gameFinished;

    private int change;

    public BlackJackOutputGameData(Game game, boolean gameFinished, int change){
        this.game = game;
        this.gameFinished = gameFinished;
        this.change = change;
    }

    public boolean gameFinished(){return this.gameFinished;}

    public int getChange() {
        return this.change;
    }

    public Game getGame(){return this.game;}


}
