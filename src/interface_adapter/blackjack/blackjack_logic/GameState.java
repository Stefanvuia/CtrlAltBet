package interface_adapter.blackjack.blackjack_logic;

import entity.Card;
import entity.BlackJackGameInterface;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private String username = "";

    private int bet = 0;

    private BlackJackGameInterface blackJackGameInterface = null;

    private String gameMessage = "";

    private boolean gameEnd = false;

    public GameState(GameState copy) {
        username = copy.username;
        bet = copy.bet;
        blackJackGameInterface = copy.blackJackGameInterface;
        gameMessage = copy.gameMessage;
        gameEnd = copy.gameEnd;
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

    public BlackJackGameInterface getGame() {
        return blackJackGameInterface;
    }

    public void setGame(BlackJackGameInterface blackJackGameInterface) {
        this.blackJackGameInterface = blackJackGameInterface;
    }

    public List<String> getPlayerHandImg() {
        ArrayList<String> imgs = new ArrayList<String>();
        for (Card card : blackJackGameInterface.getPlayer().getHand()) {
            imgs.add(card.getImg());
        }

        return imgs;
    }

    public List<String> getDealerHandImg() {
        ArrayList<String> imgs = new ArrayList<String>();
        for (Card card : blackJackGameInterface.getDealer().getHand()) {
            imgs.add(card.getImg());
        }

        return imgs;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }
}
