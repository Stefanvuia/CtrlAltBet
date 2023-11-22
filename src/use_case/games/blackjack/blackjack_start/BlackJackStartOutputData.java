package use_case.games.blackjack.blackjack_start;

import entity.game_logic.BlackJackPlayer;
import entity.game_logic.BlackJackGameInterface;

public class BlackJackStartOutputData {
    private final BlackJackGameInterface blackJackGameInterface;

    public BlackJackStartOutputData(BlackJackGameInterface blackJackGameInterface) {
        this.blackJackGameInterface = blackJackGameInterface;
    }

    public BlackJackGameInterface getGame() {
        return this.blackJackGameInterface;
    }

    public String getUser() {
        BlackJackPlayer user = (BlackJackPlayer) blackJackGameInterface.getPlayer();
        return user.getUsername();
    }

    public int getBet() {
        BlackJackPlayer user = (BlackJackPlayer) blackJackGameInterface.getPlayer();
        return user.getBet();
    }
}
