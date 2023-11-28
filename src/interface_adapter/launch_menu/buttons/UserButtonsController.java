package interface_adapter.launch_menu.buttons;

import use_case.launch_menu.buttons.UserButtonsInputBoundary;
import use_case.launch_menu.buttons.UserButtonsInputData;

public class UserButtonsController {
    private UserButtonsInputBoundary welcomeInteractor;

    public UserButtonsController(UserButtonsInputBoundary welcomeInteractor) {
        this.welcomeInteractor = welcomeInteractor;
    }

    public void execute(String newView) {
        welcomeInteractor.execute(new UserButtonsInputData(newView));
    }

    public void execute() {
        welcomeInteractor.execute(new UserButtonsInputData());
    }
}
