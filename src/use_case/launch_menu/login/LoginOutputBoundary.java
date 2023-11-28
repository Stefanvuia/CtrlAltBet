package use_case.launch_menu.login;

import entity.user.UserDataAccessFailed;

public interface LoginOutputBoundary {

    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(UserDataAccessFailed error);
}
