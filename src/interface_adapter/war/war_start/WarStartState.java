package interface_adapter.war.war_start;

/**
 * The WarStartState class represents the state of the War card game during the start phase.
 * It includes information such as the player's username, bet amount, available funds, and any
 * error related to the bet input. This class provides methods to retrieve and modify these
 * attributes.
 */
public class WarStartState {

    /**
     * The player's username.
     */
    private String username = "";

    /**
     * The bet amount set by the player.
     */
    private int bet = 0;

    /**
     * Any error message related to the bet input.
     */
    private String betError = null;

    /**
     * The available funds of the player.
     */
    private int funds = 0;

    /**
     * Constructs a new, empty WarStartState.
     */
    public WarStartState() {
    }

    /**
     * Retrieves the player's username.
     *
     * @return The player's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the player's username.
     *
     * @param username The new username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the bet amount set by the player.
     *
     * @return The bet amount.
     */
    public int getBet() {
        return bet;
    }

    /**
     * Sets the bet amount.
     *
     * @param bet The new bet amount to set.
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Retrieves the available funds of the player.
     *
     * @return The available funds.
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Sets the available funds.
     *
     * @param funds The new available funds to set.
     */
    public void setFunds(int funds) {
        this.funds = funds;
    }

    /**
     * Retrieves any error message related to the bet input.
     *
     * @return The error message, or null if no error.
     */
    public String getBetError() {
        return betError;
    }

    /**
     * Sets the error message related to the bet input.
     *
     * @param betError The new error message to set.
     */
    public void setBetError(String betError) {
        this.betError = betError;
    }
}
