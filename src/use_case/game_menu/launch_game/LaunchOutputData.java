package use_case.game_menu.launch_game;

/**
 * Represents output data for the result of launching a game from the game menu.
 */
public class LaunchOutputData {

    /**
     * The username of the player who initiated the game launch.
     */
    private final String username;

    /**
     * The available funds of the player after the game launch.
     */
    private final int funds;

    /**
     * The name of the launched game.
     */
    private final String game;

    /**
     * Constructs a LaunchOutputData object with the specified username, game name, and available funds.
     *
     * @param username The username of the player who initiated the game launch.
     * @param game     The name of the launched game.
     * @param funds    The available funds of the player after the game launch.
     */
    public LaunchOutputData(String username, String game, int funds) {
        this.username = username;
        this.game = game;
        this.funds = funds;
    }

    /**
     * Retrieves the username of the player who initiated the game launch.
     *
     * @return The username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the available funds of the player after the game launch.
     *
     * @return The available funds of the player.
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Retrieves the name of the launched game.
     *
     * @return The name of the launched game.
     */
    public String getGame() {
        return game;
    }
}
