package use_case.game_menu.account;

import use_case.game_menu.MenuDataAccessInterface;

/**
 * The interactor class for handling account-related operations in the game menu.
 * Implements the AccountInputBoundary interface.
 */
public class AccountInteractor implements AccountInputBoundary{

    /**
     * The presenter responsible for handling account-related output.
     */
    private final AccountOutputBoundary accountPresenter;


    /**
     * The data access interface for retrieving menu-related data.
     */
    private final MenuDataAccessInterface menuDAO;

    /**
     * Constructs a new AccountInteractor with the specified presenter and menu data access interface.
     *
     * @param accountPresenter The presenter for handling account-related output.
     * @param menuDAO           The data access interface for menu-related operations.
     */
    public AccountInteractor(AccountOutputBoundary accountPresenter, MenuDataAccessInterface menuDAO) {
        this.accountPresenter = accountPresenter;
        this.menuDAO = menuDAO;
    }

    /**
     * Executes the account operation based on the provided input data.
     *
     * @param inputData The input data for the account operation.
     */
    @Override
    public void execute(AccountInputData inputData) {
        String username = inputData.getUsername();
        accountPresenter.prepareSwapView(new AccountOutputData(username, menuDAO.getFund(username)));
    }
}
