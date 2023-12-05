package interface_adapter.game_menu.account;


import interface_adapter.account_menu.AccountInfoState;
import interface_adapter.account_menu.AccountInfoViewModel;
import interface_adapter.ViewManagerModel;
import use_case.game_menu.account.AccountOutputBoundary;
import use_case.game_menu.account.AccountOutputData;

/**
 * Presenter class responsible for preparing the view after an account-related operation in the game menu.
 * Bridges the communication between the account-related use case output and the AccountInfoViewModel.
 */
public class AccountPresenter implements AccountOutputBoundary {
    private final AccountInfoViewModel accountInfoViewModel;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new AccountPresenter with the specified account information view model
     * and view manager model.
     *
     * @param accountInfoViewModel The view model for the account information.
     * @param viewManagerModel     The model for managing the active view.
     */
    public AccountPresenter(AccountInfoViewModel accountInfoViewModel, ViewManagerModel viewManagerModel) {
        this.accountInfoViewModel = accountInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view for the account information after a successful account-related operation.
     *
     * @param accountOutputData The output data containing account-related information.
     */
    @Override
    public void prepareSwapView(AccountOutputData accountOutputData) {
        AccountInfoState currState = new AccountInfoState();
        currState.setUsername(accountOutputData.getUsername());
        currState.setFunds(accountOutputData.getFunds());
        accountInfoViewModel.setAccountInfoState(currState);
        accountInfoViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(accountInfoViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
