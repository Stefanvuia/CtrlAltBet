package interface_adapter.baccarat;

import entity.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaccaratState {
    private String username = "cakev";

    private Map<String, Integer> bet = initBetMap();

    private int fund = 5000;

    private List<Card> playerHand = new ArrayList<Card>();

    private List<Card> bankerHand = new ArrayList<Card>();

    private String gameMessage = "";

    public BaccaratState(BaccaratState copy) {
        username = copy.username;
        fund = copy.fund;
        playerHand = copy.playerHand;
        bankerHand = copy.bankerHand;
        gameMessage = copy.gameMessage;
        bet = copy.bet;
    }

    public BaccaratState() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public List<String> getPlayerHandImg() {
        List<String> imgs = new ArrayList<String>();

        for (Card card : playerHand) {
            imgs.add(card.getImg());
        }

        return imgs;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public List<String> getBankerHandImg() {
        List<String> imgs = new ArrayList<String>();

        for (Card card : bankerHand) {
            imgs.add(card.getImg());
        }

        return imgs;
    }

    public void setBankerHand(List<Card> bankerHand) {
        this.bankerHand = bankerHand;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }

    public Map<String, Integer> getBet() {
        return bet;
    }

    public void setBet(String key, Integer newBet) {
        bet.put(key, newBet);
    }

    private Map<String, Integer> initBetMap() {
        Map<String, Integer> betMap = new HashMap<>();
        betMap.put("player", 0);
        betMap.put("banker", 0);
        betMap.put("tie", 0);
        return betMap;
    }
}
