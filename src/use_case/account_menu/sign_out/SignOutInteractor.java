package use_case.account_menu.sign_out;

public class SignOutInteractor implements SignOutInputBoundary {
    private final SignOutOutputBoundary signOutPresenter;

    public SignOutInteractor(SignOutOutputBoundary signOutPresenter) {
        this.signOutPresenter = signOutPresenter;
    }

    @Override
    public void execute() {
        signOutPresenter.prepareWelcomeView();
    }
}
