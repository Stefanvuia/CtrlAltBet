package view.welcome;

public class WelcomeButtonsController {
    private WelcomeButtonsInputBoundary welcomeInteractor;

    public WelcomeButtonsController(WelcomeButtonsInputBoundary welcomeInteractor) {
        this.welcomeInteractor = welcomeInteractor;
    }

    public void execute(String newView) {
        welcomeInteractor.execute(new WelcomeButtonsInputData(newView));
    }

    public void execute() {
        welcomeInteractor.execute(new WelcomeButtonsInputData());
    }
}
