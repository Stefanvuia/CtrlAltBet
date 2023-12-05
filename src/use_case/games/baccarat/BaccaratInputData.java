package use_case.games.baccarat;

import java.util.Map;

/**
 * Represents input data for placing bets in a Baccarat game.
 */
public class BaccaratInputData {

    /**
     * The map containing bets on different outcomes (tie, banker, player).
     */
    private final Map<String, Integer> bet;

    /**
     * The username of the player placing the bets.
     */
    private final String username;

    /**
     * Constructs a BaccaratInputData object with the specified bet and username.
     *
     * @param bet      The map containing bets on different outcomes (tie, banker, player).
     * @param username The username of the player placing the bets.
     */
    public BaccaratInputData(Map<String, Integer> bet, String username) {
        this.bet = bet;
        this.username = username;
    }

    /**
     * Retrieves the map containing bets on different outcomes (tie, banker, player).
     *
     * @return The map containing bets.
     */
    public Map<String, Integer> getBet() {
        return bet;
    }

    /**
     * Retrieves the username of the player placing the bets.
     *
     * @return The username of the player.
     */
    public String getUsername() {
        return username;
    }
}
