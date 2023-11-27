package interface_adapter.game_menu.account;

import use_case.game_menu.account.AccountInputBoundary;
import use_case.game_menu.account.AccountInputData;

public class AccountController {
    private final AccountInputBoundary accountInteractor;

    public AccountController(AccountInputBoundary accountInteractor) {
        this.accountInteractor = accountInteractor;
    }

    public void execute(String username) {
        accountInteractor.execute(new AccountInputData(username));
    }
}
