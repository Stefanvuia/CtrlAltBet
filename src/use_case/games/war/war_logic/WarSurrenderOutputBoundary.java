package use_case.games.war.war_logic;

/**
 * The WarSurrenderOutputBoundary interface defines the contract for the output boundary of the
 * "Surrender" action in the War card game. Classes implementing this interface should provide
 * a method to prepare and present a view for a successful surrender action.
 */
public interface WarSurrenderOutputBoundary {

    /**
     * Prepares and presents the view for a successful surrender in the War card game.
     *
     * @param warOutputGameData The output data containing the updated game state after the surrender.
     */
    void prepareSurrenderView(WarOutputGameData warOutputGameData);
}
