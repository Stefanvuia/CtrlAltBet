package interface_adapter.blackjack.blackjack_logic;

import entity.Game;

public class TestLogicState {
    private Game game = null;
    private boolean end = false;
    public TestLogicState(){}

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setEnd(boolean end) {this.end = end;}

    public boolean getEnd() {return end;}
}
