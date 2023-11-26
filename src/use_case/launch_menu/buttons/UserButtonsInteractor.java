package use_case.launch_menu.buttons;

public class UserButtonsInteractor implements UserButtonsInputBoundary {
    UserButtonsOutputBoundary welcomeButtonsPresenter;

    public UserButtonsInteractor(UserButtonsOutputBoundary welcomeButtonsPresenter) {
        this.welcomeButtonsPresenter = welcomeButtonsPresenter;
    }

    @Override
    public void execute(UserButtonsInputData inputData) {
        if (inputData.getNewView().isEmpty()) {
            welcomeButtonsPresenter.prepareSwap(new UserButtonsOutputData("welcome"));
        } else {
            welcomeButtonsPresenter.prepareSwap(new UserButtonsOutputData(inputData.getNewView()));
        }
    }
}
