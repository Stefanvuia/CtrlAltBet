package interface_adapter.war.war_occur;

import entity.game_logic.WarGameInterface;
import use_case.games.war.war_logic.WarInputGameData;
import use_case.games.war.war_logic.WarSurrenderInputBoundary;

/**
 * The WarSurrenderController class serves as the controller responsible for initiating the
 * surrender action in the War card game. It collaborates with the corresponding interactor
 * (WarSurrenderInputBoundary) to perform the necessary actions and handle the game flow.
 */
public class WarSurrenderController {

    /**
     * The interactor responsible for handling the surrender action and interacting with the
     * logic of the War card game.
     */
    final WarSurrenderInputBoundary warSurrenderInteractor;

    /**
     * Constructs a new WarSurrenderController with the specified WarSurrenderInputBoundary.
     *
     * @param warSurrenderInteractor The interactor responsible for handling surrender actions.
     */
    public WarSurrenderController(WarSurrenderInputBoundary warSurrenderInteractor) {
        this.warSurrenderInteractor = warSurrenderInteractor;
    }

    /**
     * Executes the surrender action in the War card game by invoking the corresponding
     * interactor's execute method with the provided game data.
     *
     * @param game The WarGameInterface representing the current state of the War card game.
     */
    public void execute(WarGameInterface game) {
        warSurrenderInteractor.execute(new WarInputGameData(game));
    }
}
