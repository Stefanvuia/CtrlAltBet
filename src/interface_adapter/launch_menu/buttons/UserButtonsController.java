package interface_adapter.launch_menu.buttons;

import use_case.launch_menu.buttons.UserButtonsInputBoundary;
import use_case.launch_menu.buttons.UserButtonsInputData;

/**
 * Controller class for handling user interactions with buttons in the launch menu.
 * Acts as an intermediary between the user interface and the underlying use case logic.
 */
public class UserButtonsController {

    /**
     * The use case interactor responsible for processing user button interactions.
     */
    private final UserButtonsInputBoundary welcomeInteractor;

    /**
     * Constructs a new instance of UserButtonsController.
     *
     * @param welcomeInteractor The use case interactor for handling user button interactions.
     */
    public UserButtonsController(UserButtonsInputBoundary welcomeInteractor) {
        this.welcomeInteractor = welcomeInteractor;
    }

    /**
     * Executes the user button interaction with the provided new view information.
     *
     * @param newView The identifier for the new view triggered by the button interaction.
     */
    public void execute(String newView) {
        welcomeInteractor.execute(new UserButtonsInputData(newView));
    }

    /**
     * Executes the user button interaction without providing specific new view information.
     * Useful for button interactions that do not require additional data.
     */
    public void execute() {
        welcomeInteractor.execute(new UserButtonsInputData());
    }
}