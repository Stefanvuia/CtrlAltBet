package use_case.account_menu.update;


import entity.user.UserDataAccessFailed;

public interface UpdateOutputBoundary {
    void prepareFailView(UserDataAccessFailed error);

    void prepareSuccessView(UpdateOutputData outputData);
}
