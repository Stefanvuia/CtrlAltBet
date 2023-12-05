package use_case.games.blackjack.blackjack_start;

import entity.game_logic.BlackJackPlayer;
import entity.game_logic.BlackJackGameInterface;

/**
 * Represents output data after initiating a Blackjack game, including the game interface, user, and bet information.
 */
public class BlackJackStartOutputData {

    /**
     * The interface representing the state of the Blackjack game.
     */
    private final BlackJackGameInterface blackJackGameInterface;

    /**
     * Constructs a BlackJackStartOutputData object with the specified Blackjack game interface.
     *
     * @param blackJackGameInterface The interface representing the state of the Blackjack game.
     */
    public BlackJackStartOutputData(BlackJackGameInterface blackJackGameInterface) {
        this.blackJackGameInterface = blackJackGameInterface;
    }

    /**
     * Retrieves the interface representing the state of the Blackjack game.
     *
     * @return The Blackjack game interface.
     */
    public BlackJackGameInterface getGame() {
        return this.blackJackGameInterface;
    }

    /**
     * Retrieves the username of the player initiating the Blackjack game.
     *
     * @return The username of the player.
     */
    public String getUser() {
        BlackJackPlayer user = (BlackJackPlayer) blackJackGameInterface.getPlayer();
        return user.getUsername();
    }

    /**
     * Retrieves the bet amount placed by the player for the Blackjack game.
     *
     * @return The bet amount.
     */
    public int getBet() {
        BlackJackPlayer user = (BlackJackPlayer) blackJackGameInterface.getPlayer();
        return user.getBet();
    }
}
