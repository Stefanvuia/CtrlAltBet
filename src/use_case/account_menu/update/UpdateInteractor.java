package use_case.account_menu.update;


import entity.user.User;
import entity.user.UserDataAccessFailed;

/**
 * Handles the business logic for updating user information, specifically the user balance.
 */
public class UpdateInteractor implements UpdateInputBoundary {

    /**
     * The data access interface for updating user information.
     */
    final UpdateUserDataAccessInterface updateUserDataAccessInterface;

    /**
     * The presenter responsible for handling the output of the update operation.
     */
    final UpdateOutputBoundary userPresenter;

    /**
     * Constructs a new UpdateInteractor with the specified data access interface and presenter.
     *
     * @param updateUserDataAccessInterface The data access interface for updating user information.
     * @param userPresenter                 The presenter for handling the output of the update operation.
     */
    public UpdateInteractor(UpdateUserDataAccessInterface updateUserDataAccessInterface, UpdateOutputBoundary userPresenter) {
        this.updateUserDataAccessInterface = updateUserDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    /**
     * Updates the user's balance based on the provided input data.
     *
     * @param updateInputData The input data containing the username and the balance change.
     */
    @Override
    public void updateUser(UpdateInputData updateInputData) {
        int amount = updateInputData.getBalance();
        try {
            User user = updateUserDataAccessInterface.getUserByName(updateInputData.getUsername());
            int newSum = user.getBalance() + amount;
            if (newSum >= 0) {
                user.editBalance(amount);
                updateUserDataAccessInterface.save(user);
                userPresenter.prepareSuccessView(new UpdateOutputData(newSum));
            } else {
                userPresenter.prepareFailView(new UserDataAccessFailed("Insufficient funds"));
            }
        } catch (Exception e) { userPresenter.prepareFailView(new UserDataAccessFailed("Update user failed")); }
    }
}
