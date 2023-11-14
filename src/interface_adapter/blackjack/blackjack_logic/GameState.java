package interface_adapter.blackjack.blackjack_logic;

import entity.Game;

public class GameState {
    private String username = "";

    private int bet = 0;

    private String betError = null;

    private Game game = null;

    public GameState(GameState copy) {
        username = copy.username;
        bet = copy.bet;
        game = copy.game;
        betError = copy.betError;
    }

    public GameState(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getBetError() {
        return betError;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }
}
