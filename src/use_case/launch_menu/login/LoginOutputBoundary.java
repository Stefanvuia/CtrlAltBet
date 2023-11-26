package use_case.launch_menu.login;

import entity.user.UserCreationFailed;

public interface LoginOutputBoundary {

    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(UserCreationFailed error);
}
