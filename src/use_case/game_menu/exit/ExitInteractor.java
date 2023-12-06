package use_case.game_menu.exit;

/**
 * Represents the interactor for handling exit-related operations in the game menu.
 * Implements the ExitInputBoundary interface.
 */
public class ExitInteractor implements ExitInputBoundary {

    /**
     * The presenter responsible for handling exit-related output.
     */
    private final ExitOutputBoundary exitPresenter;

    /**
     * Constructs a new ExitInteractor with the specified exit presenter.
     *
     * @param exitPresenter The presenter responsible for handling exit-related output.
     */
    public ExitInteractor(ExitOutputBoundary exitPresenter) {
        this.exitPresenter = exitPresenter;
    }

    /**
     * Executes the exit operation by preparing the view through the exit presenter.
     */
    @Override
    public void execute() {
        exitPresenter.prepareSwapView();
    }
}
