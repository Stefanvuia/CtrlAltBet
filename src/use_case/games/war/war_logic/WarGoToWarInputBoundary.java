package use_case.games.war.war_logic;

/**
 * The WarGoToWarInputBoundary interface defines the contract for the input boundary of the
 * "Go to War" action in the War card game. Classes implementing this interface should provide
 * the execute method to handle the execution of the action based on the provided game data.
 */
public interface WarGoToWarInputBoundary {

    /**
     * Executes the "Go to War" action based on the provided game data.
     *
     * @param warInputGameData The input data containing the current game state and bet information.
     */
    void execute(WarInputGameData warInputGameData);
}
