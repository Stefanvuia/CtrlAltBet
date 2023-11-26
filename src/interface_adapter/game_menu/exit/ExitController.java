package interface_adapter.game_menu.exit;

import use_case.game_menu.exit.ExitInputBoundary;

public class ExitController {
    private final ExitInputBoundary exitInteractor;

    public ExitController(ExitInputBoundary exitInteractor) {
        this.exitInteractor = exitInteractor;
    }

    public void execute() {
        exitInteractor.execute();
    }
}
