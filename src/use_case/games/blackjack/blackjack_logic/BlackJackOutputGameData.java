package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGameInterface;

public class BlackJackOutputGameData {
    private final BlackJackGameInterface blackJackGameInterface;
    private final boolean gameFinished;

    private final int change;

    public BlackJackOutputGameData(BlackJackGameInterface blackJackGameInterface, boolean gameFinished, int change){
        this.blackJackGameInterface = blackJackGameInterface;
        this.gameFinished = gameFinished;
        this.change = change;
    }

    public boolean gameFinished(){return this.gameFinished;}

    public int getChange() {
        return this.change;
    }

    public BlackJackGameInterface getGame(){return this.blackJackGameInterface;}


}
