package use_case.launch_menu.signup;

import entity.user.UserDataAccessFailed;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(UserDataAccessFailed error);
}