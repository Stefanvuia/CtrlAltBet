package interface_adapter.account_menu.update;

import entity.user.UserDataAccessFailed;

import interface_adapter.account_menu.AccountInfoState;
import interface_adapter.account_menu.AccountInfoViewModel;

import use_case.account_menu.update.UpdateOutputBoundary;
import use_case.account_menu.update.UpdateOutputData;

/**
 * Presenter class responsible for preparing the views related to the update operation.
 * Implements the UpdateOutputBoundary to handle the interaction between the update use case
 * and the user interface.
 */
public class UpdatePresenter implements UpdateOutputBoundary {
    private final AccountInfoViewModel accountInfoViewModel;

    /**
     * Constructs a new UpdatePresenter with the specified AccountInfoViewModel.
     *
     * @param accountInfoViewModel The view model for the account information view.
     */
    public UpdatePresenter(AccountInfoViewModel accountInfoViewModel) {
        this.accountInfoViewModel = accountInfoViewModel;
    }

    /**
     * Prepares and triggers the display of an error view in case of a failed update operation.
     * Updates the AccountInfoViewModel with the error message and notifies observers.
     *
     * @param error The UserDataAccessFailed object representing the failure.
     */
    @Override
    public void prepareFailView(UserDataAccessFailed error) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        currState.setErrorMessage(error.getMessage());

        accountInfoViewModel.setAccountInfoState(currState);
        accountInfoViewModel.firePropertyChanged();
    }

    /**
     * Prepares and triggers the display of a success view after a successful update operation.
     * Updates the AccountInfoViewModel with the new funds and clears the error message, then notifies observers.
     *
     * @param outputData The UpdateOutputData object containing information about the successful update.
     */
    @Override
    public void prepareSuccessView(UpdateOutputData outputData) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        currState.setFunds(outputData.getFunds());
        currState.setErrorMessage(null);

        accountInfoViewModel.setAccountInfoState(currState);
        accountInfoViewModel.firePropertyChanged();
    }
}
