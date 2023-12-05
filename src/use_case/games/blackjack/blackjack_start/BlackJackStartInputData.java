package use_case.games.blackjack.blackjack_start;

/**
 * Represents input data for initiating a Blackjack game, including the player's username and bet amount.
 */
public class BlackJackStartInputData {

    /**
     * The username of the player initiating the Blackjack game.
     */
    private final String username;

    /**
     * The bet amount placed by the player for the Blackjack game.
     */
    private final int bet;

    /**
     * Constructs a BlackJackStartInputData object with the specified username and bet amount.
     *
     * @param username The username of the player initiating the Blackjack game.
     * @param bet      The bet amount placed by the player for the Blackjack game.
     */
    public BlackJackStartInputData(String username, int bet) {
        this.username = username;
        this.bet = bet;
    }

    /**
     * Retrieves the username of the player initiating the Blackjack game.
     *
     * @return The player's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the bet amount placed by the player for the Blackjack game.
     *
     * @return The bet amount.
     */
    public int getBet() {
        return this.bet;
    }
}
