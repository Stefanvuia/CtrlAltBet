package interface_adapter.baccarat;

import entity.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BaccaratGameState {
    private List<Image> playerHand = new ArrayList<>();

    private List<Image> bankerHand = new ArrayList<>();

    private String gameMessage = "";

    public BaccaratGameState() {}

    public List<Image> getPlayerHandImg() {
        return playerHand;
    }

    public void setPlayerHand(List<Image> playerHand) {
        this.playerHand = playerHand;
    }

    public List<Image> getBankerHandImg() {
        return bankerHand;
    }

    public void setBankerHand(List<Image> bankerHand) {
        this.bankerHand = bankerHand;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }
}
