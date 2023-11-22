package use_case.menu.exit;

import use_case.menu.MenuDataAccessInterface;

public class ExitInteractor implements ExitInputBoundary{

    private final ExitOutputBoundary exitPresenter;

    public ExitInteractor(ExitOutputBoundary exitPresenter) {
        this.exitPresenter = exitPresenter;
    }

    @Override
    public void execute() {
        exitPresenter.prepareSwapView();
    }
}
