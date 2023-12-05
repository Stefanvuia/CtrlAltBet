package use_case.account_menu.sign_out;

/**
 * Represents the interactor for handling sign-out operations in the account menu.
 * Implements the SignOutInputBoundary interface.
 */
public class SignOutInteractor implements SignOutInputBoundary {

    /**
     * The presenter responsible for handling sign-out-related output.
     */
    private final SignOutOutputBoundary signOutPresenter;

    /**
     * Constructs a new SignOutInteractor with the specified sign-out presenter.
     *
     * @param signOutPresenter The presenter responsible for handling sign-out-related output.
     */
    public SignOutInteractor(SignOutOutputBoundary signOutPresenter) {
        this.signOutPresenter = signOutPresenter;
    }

    /**
     * Executes the sign-out operation by preparing the view through the sign-out presenter.
     */
    @Override
    public void execute() {
        signOutPresenter.prepareWelcomeView();
    }
}
