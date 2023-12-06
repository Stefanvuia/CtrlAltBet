package use_case.games.war.war_started;

/**
 * The WarStartInputData class represents the input data for the "Start War" action in the War card game.
 * It encapsulates the username and initial bet provided when starting a new game.
 */
public class WarStartInputData {

    /**
     * The username for starting the game.
     */
    private final String username;

    /**
     * The initial bet for starting the game.
     */
    private final int bet;

    /**
     * The initial bet for starting the game.
     */
    public WarStartInputData(String username, int bet) {
        this.username = username;
        this.bet = bet;
    }

    /**
     * Retrieves the username for starting the game.
     *
     * @return The username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the initial bet for starting the game.
     *
     * @return The initial bet.
     */
    public int getBet() {
        return this.bet;
    }
}
