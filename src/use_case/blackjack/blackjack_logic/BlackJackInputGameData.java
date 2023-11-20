package use_case.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGame;
import entity.game_logic.BlackJackGameInterface;

public class BlackJackInputGameData {
    private BlackJackGameInterface blackJackGameInterface;

    public BlackJackInputGameData(BlackJackGameInterface blackJackGameInterface){
        this.blackJackGameInterface = blackJackGameInterface;
    }

    BlackJackGame getGame(){ return (BlackJackGame) this.blackJackGameInterface; }
}
