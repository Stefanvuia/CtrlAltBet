package interface_adapter.baccarat;

import use_case.games.baccarat.BaccaratInputBoundary;
import use_case.games.baccarat.BaccaratInputData;

import java.util.Map;

/**
 * Controller class for handling Baccarat game-related user interactions.
 */
public class BaccaratController {

    /**
     * The BaccaratInputBoundary instance responsible for processing Baccarat game inputs.
     */
    final BaccaratInputBoundary baccaratInteractor;

    /**
     * Constructs a BaccaratController with the specified BaccaratInputBoundary.
     *
     * @param baccaratInteractor The BaccaratInputBoundary instance to handle Baccarat game inputs.
     */
    public BaccaratController(BaccaratInputBoundary baccaratInteractor) {
        this.baccaratInteractor = baccaratInteractor;
    }

    /**
     * Executes a Baccarat game action based on the provided bet and username.
     *
     * @param bet      A Map representing the bet amounts for different outcomes (e.g., "player", "banker", "tie").
     * @param username The username of the player initiating the Baccarat game action.
     */
    public void execute(Map<String, Integer> bet, String username) {
        BaccaratInputData inputData = new BaccaratInputData(bet, username);
        baccaratInteractor.execute(inputData);
    }
}
