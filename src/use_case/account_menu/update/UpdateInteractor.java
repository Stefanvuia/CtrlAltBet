package use_case.account_menu.update;


import entity.user.User;
import entity.user.UserDataAccessFailed;

public class UpdateInteractor implements UpdateInputBoundary {

    final UpdateUserDataAccessInterface updateUserDataAccessInterface;

    final UpdateOutputBoundary userPresenter;

    public UpdateInteractor(UpdateUserDataAccessInterface updateUserDataAccessInterface, UpdateOutputBoundary userPresenter) {
        this.updateUserDataAccessInterface = updateUserDataAccessInterface;
        this.userPresenter = userPresenter;
    }

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
        } catch (Exception e) {
            userPresenter.prepareFailView(new UserDataAccessFailed("Update user failed"));
        }
    }
}
