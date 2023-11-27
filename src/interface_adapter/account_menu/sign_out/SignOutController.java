package interface_adapter.account_menu.sign_out;

import use_case.account_menu.sign_out.SignOutInputBoundary;

public class SignOutController {
    private SignOutInputBoundary signOutInteractor;

    public SignOutController(SignOutInputBoundary signOutInteractor) {
        this.signOutInteractor = signOutInteractor;
    }

    public void execute() {
        signOutInteractor.execute();
    }
}
