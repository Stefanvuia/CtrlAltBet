package history;

import history.UserHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommonUserHistory implements UserHistory {

    private String username;
    private ArrayList<Double> blackjackPayout;
    private ArrayList<Double> baccaratPayout;
    private ArrayList<Double> warPayout;
    private Map<String, ArrayList<Double>> payoutMap = new HashMap<>();

    public CommonUserHistory(String username) {
        this.username = username;
        this.blackjackPayout = new ArrayList<>();
        this.baccaratPayout = new ArrayList<>();
        this.warPayout = new ArrayList<>();

        payoutMap.put("blackjack", this.blackjackPayout);
        payoutMap.put("baccarat", this.baccaratPayout);
        payoutMap.put("war", this.warPayout);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public ArrayList<Double> getPayouts(String game) {
        return payoutMap.get(game);
    }

    @Override
    public void setPayouts(String game, Double values) {
        payoutMap.get(game).add(values);
    }

    @Override
    public String payoutFormatter(String game) {
        StringBuilder sb = new StringBuilder();
        for (Double d : payoutMap.get(game)) {
            // Append the double to the StringBuilder and then append a space
            sb.append(d).append(" ");
        }

        // Convert the StringBuilder to a String.
        return sb.toString().trim();
    }
}
