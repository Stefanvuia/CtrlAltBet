package use_case.games.war.war_logic;

/**
 * The WarGoToWarOutputBoundary interface defines the contract for the output boundary of the
 * "Go to War" action in the War card game. Classes implementing this interface should provide
 * methods to prepare and present views for successful payouts and handling failures.
 */
public interface WarGoToWarOutputBoundary {

    /**
     * Prepares and presents the view for a successful payout in the War card game.
     *
     * @param warOutputGameData The output data containing the updated game state after the action.
     */
    void preparePayoutView(WarOutputGameData warOutputGameData);

    /**
     * Prepares and presents the view for a failure in the "Go to War" action, providing
     * an error message.
     *
     * @param error The error message indicating the cause of the failure.
     */
    void prepareFailView(String error);
}
