package use_case.blackjack.blackjack_start;

import entity.Game;

public class BlackJackStartOutputData {
    private Game game;

    public BlackJackStartOutputData(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }
}
