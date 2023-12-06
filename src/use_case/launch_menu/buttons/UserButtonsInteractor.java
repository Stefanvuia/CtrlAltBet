package use_case.launch_menu.buttons;

/**
 * The {@code UserButtonsInteractor} class implements the {@code UserButtonsInputBoundary} interface
 * and is responsible for handling user button interactions in the launch menu.
 *
 * <p>This class collaborates with a {@code UserButtonsOutputBoundary} to prepare and present the output
 * based on the user's input.</p>
 */
public class UserButtonsInteractor implements UserButtonsInputBoundary {

    /**
     * The presenter responsible for handling the output of user button interactions.
     */
    private final UserButtonsOutputBoundary welcomeButtonsPresenter;

    /**
     * Constructs a {@code UserButtonsInteractor} with the specified {@code welcomeButtonsPresenter}.
     *
     * @param welcomeButtonsPresenter The presenter responsible for handling the output of user button interactions.
     */
    public UserButtonsInteractor(UserButtonsOutputBoundary welcomeButtonsPresenter) {
        this.welcomeButtonsPresenter = welcomeButtonsPresenter;
    }

    /**
     * Executes the user button interaction based on the provided {@code inputData}.
     *
     * <p>If the new view in the input data is empty, it prepares to swap to the "welcome" view; otherwise,
     * it prepares to swap to the specified new view.</p>
     *
     * @param inputData The input data containing information about the user's interaction with buttons.
     */
    @Override
    public void execute(UserButtonsInputData inputData) {
        if (inputData.getNewView().isEmpty()) {
            welcomeButtonsPresenter.prepareSwap(new UserButtonsOutputData("welcome"));
        } else {
            welcomeButtonsPresenter.prepareSwap(new UserButtonsOutputData(inputData.getNewView()));
        }
    }
}