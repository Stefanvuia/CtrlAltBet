package use_case.games.baccarat;

import entity.Card;

import java.util.ArrayList;
import java.util.List;

public class BaccaratOutputData {
    private final String message;
    private final int endFunds;

    private List<Card> playerHand = new ArrayList<Card>();

    private List<Card> bankerHand = new ArrayList<Card>();

    public BaccaratOutputData(String message, int endFunds) {
        this.message = message;
        this.endFunds = endFunds;
    }

    public BaccaratOutputData(String message, int endFunds, List<Card> playerHand, List<Card> bankerHand) {
        this.message = message;
        this.endFunds = endFunds;
        this.playerHand = playerHand;
        this.bankerHand = bankerHand;
    }

    public String getMessage() {
        return message;
    }

    public int getEndFunds() {
        return endFunds;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getBankerHand() {
        return bankerHand;
    }
}
