package interface_adapter.menu.exit;

import use_case.menu.exit.ExitInputBoundary;

public class ExitController {
    private final ExitInputBoundary exitInteractor;

    public ExitController(ExitInputBoundary exitInteractor) {
        this.exitInteractor = exitInteractor;
    }

    public void execute() {
        exitInteractor.execute();
    }
}
