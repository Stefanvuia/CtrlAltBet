package view.welcome;

public class WelcomeButtonsInteractor implements WelcomeButtonsInputBoundary{
    WelcomeButtonsOutputBoundary welcomeButtonsPresenter;

    public WelcomeButtonsInteractor(WelcomeButtonsOutputBoundary welcomeButtonsPresenter) {
        this.welcomeButtonsPresenter = welcomeButtonsPresenter;
    }

    @Override
    public void execute(WelcomeButtonsInputData inputData) {
        if (inputData.getNewView().isEmpty()) {
            welcomeButtonsPresenter.prepareSwap(new WelcomeButtonsOutputData("main"));
        } else {
            welcomeButtonsPresenter.prepareSwap(new WelcomeButtonsOutputData(inputData.getNewView()));
        }
    }
}
