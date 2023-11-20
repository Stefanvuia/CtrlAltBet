package use_case.baccarat;

import java.util.Map;

public class BaccaratInputData {
    private final Map<String, Integer> bet;

    private final String username;

    public BaccaratInputData(Map<String, Integer> bet, String username) {
        this.bet = bet;
        this.username = username;
    }

    public Map<String, Integer> getBet() {
        return bet;
    }

    public String getUsername() {
        return username;
    }
}
