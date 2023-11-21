package use_case.blackjack.blackjack_logic;

import entity.BlackJackGame;
import entity.BlackJackGameInterface;

public class BlackJackInputGameData {
    private BlackJackGameInterface blackJackGameInterface;

    public BlackJackInputGameData(BlackJackGameInterface blackJackGameInterface){
        this.blackJackGameInterface = blackJackGameInterface;
    }

    BlackJackGame getGame(){ return (BlackJackGame) this.blackJackGameInterface; }
}
