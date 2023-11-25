package users.signup;

import interface_adapter.UserCreationFailed;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(UserCreationFailed error);
}