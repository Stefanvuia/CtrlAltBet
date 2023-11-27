package users.update;


import entity.user.User;

public class UpdateInteractor implements UpdateInputBoundary {

    final UpdateUserDataAccessInterface updateUserDataAccessInterface;

    final UpdateOutputBoundary userPresenter;

    public UpdateInteractor(UpdateUserDataAccessInterface updateUserDataAccessInterface, UpdateOutputBoundary userPresenter) {
        this.updateUserDataAccessInterface = updateUserDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public void updateUser(UpdateInputData updateInputData) {
        try {
            User user = updateUserDataAccessInterface.getUserByName(updateInputData.getUsername());
            user.setBalance(updateInputData.getBalance());
            updateUserDataAccessInterface.save(user);
        } catch (Exception e) {
            userPresenter.prepareFailView("Update user failed");
        }
    }
}
