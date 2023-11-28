package interface_adapter.game_menu.account;

import interface_adapter.account_menu.AccountInfoState;
import interface_adapter.account_menu.AccountInfoViewModel;
import interface_adapter.ViewManagerModel;
import use_case.game_menu.account.AccountOutputBoundary;
import use_case.game_menu.account.AccountOutputData;

public class AccountPresenter implements AccountOutputBoundary {
    private final AccountInfoViewModel accountInfoViewModel;

    private final ViewManagerModel viewManagerModel;

    public AccountPresenter(AccountInfoViewModel accountInfoViewModel, ViewManagerModel viewManagerModel) {
        this.accountInfoViewModel = accountInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }

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
