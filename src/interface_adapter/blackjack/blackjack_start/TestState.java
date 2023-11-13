package interface_adapter.blackjack.blackjack_start;

import entity.Game;

public class TestState {
    private Game game = null;
    public TestState(){}

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
