package use_case.start;

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