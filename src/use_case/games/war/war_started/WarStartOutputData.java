package use_case.games.war.war_started;

import entity.game_logic.WarPlayer;
import entity.game_logic.WarGameInterface;

/**
 * The WarStartOutputData class represents the output data for the "Start War" action in the War card game.
 * It encapsulates the updated game state after a successful start, including the game interface, user, and bet information.
 */
public class WarStartOutputData {

    /** The updated game state after a successful start. */
    private final WarGameInterface game;

    /**
     * Constructs a new WarStartOutputData with the specified game interface.
     *
     * @param game The updated game state after a successful start.
     */
    public WarStartOutputData(WarGameInterface game) {
        this.game = game;
    }

    /**
     * Retrieves the updated game state after a successful start.
     *
     * @return The game interface.
     */
    public WarGameInterface getGame() {
        return this.game;
    }

    /**
     * Retrieves the username of the player who started the game.
     *
     * @return The username of the player.
     */
    public String getUser() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getUsername();
    }

    /**
     * Retrieves the initial bet of the player who started the game.
     *
     * @return The initial bet.
     */
    public int getBet() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getBet();
    }
}
