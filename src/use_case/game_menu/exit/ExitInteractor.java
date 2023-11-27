package use_case.game_menu.exit;

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
