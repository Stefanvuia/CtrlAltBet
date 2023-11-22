package users.login;

public interface LoginOutputBoundary {

    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
}
