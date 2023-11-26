package interface_adapter.account_menu.update;

import entity.user.UserDataAccessFailed;
import interface_adapter.AccountInfoState;
import interface_adapter.AccountInfoViewModel;
import use_case.account_menu.update.UpdateOutputBoundary;
import use_case.account_menu.update.UpdateOutputData;

public class UpdatePresenter implements UpdateOutputBoundary {
    private final AccountInfoViewModel accountInfoViewModel;

    public UpdatePresenter(AccountInfoViewModel accountInfoViewModel) {
        this.accountInfoViewModel = accountInfoViewModel;
    }

    @Override
    public void prepareFailView(UserDataAccessFailed error) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        currState.setErrorMessage(error.getMessage());

        accountInfoViewModel.setAccountInfoState(currState);
        accountInfoViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(UpdateOutputData outputData) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        currState.setFunds(outputData.getFunds());
        currState.setErrorMessage(null);

        accountInfoViewModel.setAccountInfoState(currState);
        accountInfoViewModel.firePropertyChanged();
    }
}
