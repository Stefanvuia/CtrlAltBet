package interface_adapter.blackjack.blackjack_logic;

import entity.Game;

public class TestLogicViewModel {
    private TestLogicState state = new TestLogicState();

    public TestLogicState getState() {return state;}

    public void setGameState(Game game) {state.setGame(game);}

    public void setGameEnd(boolean end) {state.setEnd(end);}
}
