package interface_adapter.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGameInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGameState {
    private int bet = 0;

    private List<Image> playerImages = new ArrayList<>();

    private List<Image> dealerImages = new ArrayList<>();

    private BlackJackGameInterface blackJackGameInterface = null;

    private String gameMessage = "";

    private boolean gameEnd = false;

    public BlackJackGameState() {
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

    public List<Image> getPlayerImages() {
        return playerImages;
    }

    public void setPlayerImages(List<Image> playerImages) {
        this.playerImages = playerImages;
    }

    public List<Image> getDealerImages() {
        return dealerImages;
    }

    public void setDealerImages(List<Image> dealerImages) {
        this.dealerImages = dealerImages;
    }
}
