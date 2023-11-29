package interface_adapter.war;

import entity.game_logic.WarGameInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WarGameState {
    private int bet = 0;

    private java.util.List<Image> playerImages = new ArrayList<>();

    private java.util.List<Image> dealerImages = new ArrayList<>();

    private WarGameInterface warGameInterface = null;

    public WarGameState(){}

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public WarGameInterface getGame() {
        return warGameInterface;
    }

    public void setGame(WarGameInterface warGameInterface) {
        this.warGameInterface = warGameInterface;
    }

    public java.util.List<Image> getPlayerImages() {
        return playerImages;
    }

    public void setPlayerImages(java.util.List<Image> playerImages) {
        this.playerImages = playerImages;
    }

    public java.util.List<Image> getDealerImages() {
        return dealerImages;
    }

    public void setDealerImages(List<Image> dealerImages) {
        this.dealerImages = dealerImages;
    }
}