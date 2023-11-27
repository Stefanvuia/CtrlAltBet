package interface_adapter.baccarat;

import use_case.games.baccarat.BaccaratInputBoundary;
import use_case.games.baccarat.BaccaratInputData;

import java.util.Map;

public class BaccaratController {
    final BaccaratInputBoundary baccaratInteractor;

    public BaccaratController(BaccaratInputBoundary baccaratInteractor) {
        this.baccaratInteractor = baccaratInteractor;
    }

    public void execute(Map<String, Integer> bet, String username) {
        BaccaratInputData inputData = new BaccaratInputData(bet, username);

        baccaratInteractor.execute(inputData);
    }
}
