package interface_adapter.baccarat;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the state of the Baccarat game at the start, including user information, betting details, fund, and error messages.
 */
public class BaccaratStartState {
    /**
     * The username of the player.
     */
    private String username = "";

    /**
     * The betting amounts for different outcomes (player, banker, tie).
     */
    private final Map<String, Integer> bet = initBetMap();

    /**
     * The available funds for the player.
     */
    private int fund = 0;

    /**
     * The error message associated with the game state.
     */
    private String errorMessage = "";

    /**
     * Constructs an instance of the BaccaratStartState class.
     */
    public BaccaratStartState() {}

    /**
     * Gets the username of the player.
     *
     * @return The username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the player.
     *
     * @param username The new username of the player.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the available funds for the player.
     *
     * @return The available funds for the player.
     */
    public int getFund() {
        return fund;
    }

    /**
     * Sets the available funds for the player.
     *
     * @param fund The new available funds for the player.
     */
    public void setFund(int fund) {
        this.fund = fund;
    }

    /**
     * Gets the error message associated with the game state.
     *
     * @return The error message associated with the game state.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message associated with the game state.
     *
     * @param errorMessage The new error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the betting amounts for different outcomes (player, banker, tie).
     *
     * @return The betting amounts for different outcomes.
     */
    public Map<String, Integer> getBet() {
        return bet;
    }

    /**
     * Sets the betting amount for a specific outcome.
     *
     * @param key    The outcome key ("player", "banker", "tie").
     * @param newBet The new betting amount for the specified outcome.
     */
    public void setBet(String key, Integer newBet) {
        bet.put(key, newBet);
    }

    /**
     * Initializes the betting amounts map with default values.
     *
     * @return The initialized betting amounts map.
     */
    private Map<String, Integer> initBetMap() {
        Map<String, Integer> betMap = new HashMap<>();
        betMap.put("player", 0);
        betMap.put("banker", 0);
        betMap.put("tie", 0);
        return betMap;
    }
}
