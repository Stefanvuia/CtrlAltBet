package use_case.games.war.war_started;

/**
 * The WarStartInputBoundary interface defines the contract for the input boundary of the
 * "Start War" action in the War card game. Classes implementing this interface should provide
 * the execute method to handle the execution of the action based on the provided input data.
 */
public interface WarStartInputBoundary {

    /**
     * Executes the "Start War" action based on the provided input data.
     *
     * @param warStartInputData The input data containing the username and initial bet for starting the game.
     */
    void execute(WarStartInputData warStartInputData);
}
