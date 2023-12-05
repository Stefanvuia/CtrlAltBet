package interface_adapter.game_menu.account;

import use_case.game_menu.account.AccountInputBoundary;
import use_case.game_menu.account.AccountInputData;

/**
 * Controller class for handling account-related operations in the game menu.
 * Acts as an intermediary between the user interface and the AccountInputBoundary.
 */
public class AccountController {
    private final AccountInputBoundary accountInteractor;

    /**
     * Constructs a new AccountController with the specified account interactor.
     *
     * @param accountInteractor The interactor for handling account-related use cases.
     */
    public AccountController(AccountInputBoundary accountInteractor) {
        this.accountInteractor = accountInteractor;
    }

    /**
     * Executes the account-related operation for the given username.
     *
     * @param username The username for which the account operation is performed.
     */
    public void execute(String username) {
        accountInteractor.execute(new AccountInputData(username));
    }
}
