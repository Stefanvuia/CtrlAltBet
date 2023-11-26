package use_case.launch_menu.signup;

import entity.user.UserCreationFailed;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(UserCreationFailed error);
}