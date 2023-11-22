package interface_adapter.launch_game;

import use_case.launch_game.LaunchInputBoundary;
import use_case.launch_game.LaunchInputData;

public class LaunchController {
    final LaunchInputBoundary launchInteractor;

    public LaunchController(LaunchInputBoundary launchInteractor) {
        this.launchInteractor = launchInteractor;
    }

    public void execute(String username, String game) {
        launchInteractor.execute(new LaunchInputData(username, game));
    }
}
