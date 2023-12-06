package use_case.games.war.war_logic;

import entity.game_logic.WarGameInterface;
import entity.game_logic.WarPlayer;

/**
 * The WarOutputGameData class represents the output data after the "Go to War" action in the War card game.
 * It encapsulates the updated game state (WarGameInterface) and provides methods to retrieve user-related
 * information such as the username and bet amount after the action.
 */
public class WarOutputGameData {

    /**
     * The updated game state after the action.
     */
    private final WarGameInterface game;

    /**
     * Constructs a new WarOutputGameData with the specified updated game state.
     *
     * @param game The updated game state.
     */
    public WarOutputGameData(WarGameInterface game) {
        this.game = game;
    }

    /**
     * Retrieves the updated game state after the action.
     *
     * @return The WarGameInterface instance representing the updated game state.
     */
    public WarGameInterface getGame() {
        return this.game;
    }

    /**
     * Retrieves the username of the player associated with the updated game state.
     *
     * @return The username of the player.
     */
    public String getUser() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getUsername();
    }

    /**
     * Retrieves the bet amount of the player associated with the updated game state.
     *
     * @return The bet amount of the player.
     */
    public int getBet() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getBet();
    }
}
