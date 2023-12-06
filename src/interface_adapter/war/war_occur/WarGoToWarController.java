package interface_adapter.war.war_occur;

import entity.game_logic.WarGameInterface;
import use_case.games.war.war_logic.WarGoToWarInputBoundary;
import use_case.games.war.war_logic.WarInputGameData;

/**
 * The WarGoToWarController class serves as the controller responsible for initiating the "Go to War"
 * action in the War card game. It collaborates with the corresponding interactor
 * (WarGoToWarInputBoundary) to perform the necessary actions and handle the game flow.
 */
public class WarGoToWarController {

    /**
     * The interactor responsible for handling the "Go to War" action and interacting with the
     * business logic of the War card game.
     */
    final WarGoToWarInputBoundary warGoToWarInteractor;

    /**
     * Constructs a new WarGoToWarController with the specified WarGoToWarInputBoundary.
     *
     * @param warGoToWarInteractor The interactor responsible for handling "Go to War" actions.
     */
    public WarGoToWarController(WarGoToWarInputBoundary warGoToWarInteractor) {
        this.warGoToWarInteractor = warGoToWarInteractor;
    }

    /**
     * Executes the "Go to War" action in the War card game by invoking the corresponding
     * interactor's execute method with the provided game data.
     *
     * @param game The WarGameInterface representing the current state of the War card game.
     */
    public void execute(WarGameInterface game) {
        warGoToWarInteractor.execute(new WarInputGameData(game));
    }
}
