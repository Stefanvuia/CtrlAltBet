package use_case.game_menu.launch_game;

/**
 * Represents input data for launching a game from the game menu.
 */
public class LaunchInputData {

    /**
     * The username of the player initiating the game launch.
     */
    private final String username;

    /**
     * The selected game to be launched.
     */
    private final String game;

    /**
     * Constructs a LaunchInputData object with the specified username and selected game.
     *
     * @param username The username of the player initiating the game launch.
     * @param game     The selected game to be launched.
     */
    public LaunchInputData(String username, String game) {
        this.username = username;
        this.game = game;
    }

    /**
     * Retrieves the username of the player initiating the game launch.
     *
     * @return The username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the selected game to be launched.
     *
     * @return The selected game.
     */
    public String getGame() {
        return game;
    }
}
