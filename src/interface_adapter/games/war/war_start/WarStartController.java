package interface_adapter.games.war.war_start;

import use_case.games.war.war_started.WarStartInputBoundary;
import use_case.games.war.war_started.WarStartInputData;

/**
 * The WarStartController class serves as the controller responsible for initiating the
 * start action in the War card game. It collaborates with the corresponding interactor
 * (WarStartInputBoundary) to perform the necessary actions and handle the game flow.
 */
public class WarStartController {

    /**
     * The interactor responsible for handling the start action and interacting with the
     * business logic of the War card game.
     */
    final WarStartInputBoundary startUseCaseInteractor;

    /**
     * Constructs a new WarStartController with the specified WarStartInputBoundary.
     *
     * @param startUseCaseInteractor The interactor responsible for handling start actions.
     */
    public WarStartController(WarStartInputBoundary startUseCaseInteractor) {
        this.startUseCaseInteractor = startUseCaseInteractor;
    }

    /**
     * Executes the start action in the War card game by invoking the corresponding
     * interactor's execute method with the provided username and bet amount.
     *
     * @param username The username of the player starting the game.
     * @param bet      The bet amount set by the player.
     */
    public void execute(String username, int bet) {
        WarStartInputData startInputData = new WarStartInputData(username, bet);

        startUseCaseInteractor.execute(startInputData);
    }
}
