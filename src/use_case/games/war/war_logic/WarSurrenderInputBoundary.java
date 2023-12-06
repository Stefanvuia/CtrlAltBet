package use_case.games.war.war_logic;

/**
 * The WarSurrenderInputBoundary interface defines the contract for the input boundary of the
 * "Surrender" action in the War card game. Classes implementing this interface should provide
 * the execute method to handle the execution of the action based on the provided game data.
 */
public interface WarSurrenderInputBoundary {

    /**
     * Executes the "Surrender" action based on the provided game data.
     *
     * @param warInputGameData The input data containing the current game state and bet information.
     */
    void execute(WarInputGameData warInputGameData);
}
