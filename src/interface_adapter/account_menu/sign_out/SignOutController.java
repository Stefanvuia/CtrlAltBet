package interface_adapter.account_menu.sign_out;

import use_case.account_menu.sign_out.SignOutInputBoundary;

/**
 * Controller class responsible for managing sign-out operations in the account menu.
 * Acts as a bridge between the user interface and the SignOutInputBoundary use case.
 */
public class SignOutController {
    private SignOutInputBoundary signOutInteractor;

    /**
     * Constructs a new SignOutController with the specified SignOutInputBoundary.
     *
     * @param signOutInteractor The use case interactor for handling sign-out operations.
     */
    public SignOutController(SignOutInputBoundary signOutInteractor) {
        this.signOutInteractor = signOutInteractor;
    }

    /**
     * Executes the sign-out operation by invoking the associated SignOutInputBoundary.
     */
    public void execute() {
        signOutInteractor.execute();
    }
}
