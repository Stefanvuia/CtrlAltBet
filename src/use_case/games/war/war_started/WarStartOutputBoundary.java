package use_case.games.war.war_started;

/**
 * The WarStartOutputBoundary interface defines the contract for the output boundary of the
 * "Start War" action in the War card game. Classes implementing this interface should provide
 * methods to prepare and present views for successful outcomes and failure scenarios.
 */
public interface WarStartOutputBoundary {

    /**
     * Prepares and presents the view for a successful start of the War game.
     *
     * @param outputData The output data containing the updated game state after a successful start.
     */
    void prepareWarIngameView(WarStartOutputData outputData);

    /**
     * Prepares and presents the view for the "Go to War" scenario in the War game.
     *
     * @param outputData The output data containing the updated game state when proceeding to War.
     */
    void prepareGoToWarView(WarStartOutputData outputData);

    /**
     * Prepares and presents the view for a failure scenario during the "Start War" action.
     *
     * @param error The error message indicating the reason for the failure.
     */
    void prepareFailView(String error);
}
