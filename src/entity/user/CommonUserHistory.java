package entity.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the historical data and payouts for a common user in various games.
 */
public class CommonUserHistory implements UserHistory {

    private String username;
    private ArrayList<Double> blackjackPayout;
    private ArrayList<Double> baccaratPayout;
    private ArrayList<Double> warPayout;
    private Map<String, ArrayList<Double>> payoutMap = new HashMap<>();

    /**
     * Constructs a new instance of CommonUserHistory with the specified username.
     *
     * @param username The username associated with this user history.
     */
    public CommonUserHistory(String username) {
        this.username = username;
        this.blackjackPayout = new ArrayList<>();
        this.baccaratPayout = new ArrayList<>();
        this.warPayout = new ArrayList<>();

        payoutMap.put("blackjack", this.blackjackPayout);
        payoutMap.put("baccarat", this.baccaratPayout);
        payoutMap.put("war", this.warPayout);
    }

    /**
     * Gets the username associated with this user history.
     *
     * @return The username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Gets the list of payouts for the specified game.
     *
     * @param game The name of the game.
     * @return The list of payouts for the specified game.
     */
    @Override
    public ArrayList<Double> getPayouts(String game) {
        return payoutMap.get(game);
    }

    /**
     * Sets the payouts for the specified game.
     *
     * @param game   The name of the game.
     * @param values The payouts to be added.
     */
    @Override
    public void setPayouts(String game, Double values) {
        payoutMap.get(game).add(values);
    }

    /**
     * Formats the payouts for the specified game as a string.
     *
     * @param game The name of the game.
     * @return A formatted string containing the payouts for the specified game.
     */
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
