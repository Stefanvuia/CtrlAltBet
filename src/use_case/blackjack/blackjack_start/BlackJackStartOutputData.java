package use_case.blackjack.blackjack_start;

import entity.BlackJackPlayer;
import entity.Game;

public class BlackJackStartOutputData {
    private Game game;

    public BlackJackStartOutputData(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public String getUser() {
        BlackJackPlayer user = (BlackJackPlayer) game.getPlayer();
        return user.getUsername();
    }

    public int getBet() {
        BlackJackPlayer user = (BlackJackPlayer) game.getPlayer();
        return user.getBet();
    }
}
