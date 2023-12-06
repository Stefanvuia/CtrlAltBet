package use_case.games.war.war_logic;


import entity.game_logic.WarGame;
import entity.game_logic.WarGameInterface;
import entity.game_logic.WarPlayer;

/**
 * The WarInputGameData class represents the input data for the "Go to War" action in the War card game.
 * It encapsulates the current game state (WarGameInterface) and provides methods to retrieve user-related
 * information such as the username and bet amount.
 */
public class WarInputGameData {

    /** The current game state. */
    private final WarGameInterface game;


    /**
     * Constructs a new WarInputGameData with the specified game state.
     *
     * @param game The current game state.
     */
    public WarInputGameData(WarGameInterface game){

        this.game = game;
    }

    /**
     * Retrieves the specific WarGame instance from the game interface.
     *
     * @return The WarGame instance.
     */
    WarGame getGame(){ return (WarGame) this.game; }

    /**
     * Retrieves the username of the player associated with the current game.
     *
     * @return The username of the player.
     */
    public String getUser() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getUsername();
    }

    /**
     * Retrieves the bet amount of the player associated with the current game.
     *
     * @return The bet amount of the player.
     */
    public int getBet() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getBet();
    }
}
