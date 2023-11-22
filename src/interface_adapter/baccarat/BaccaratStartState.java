package interface_adapter.baccarat;

import java.util.HashMap;
import java.util.Map;

public class BaccaratStartState {
    private String username = "cakev";

    private Map<String, Integer> bet = initBetMap();

    private int fund = 5000;

    private String errorMessage = "";

    public BaccaratStartState() {}

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
