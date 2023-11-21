package users.login;

import entities.User;

import java.time.LocalDateTime;

public class LoginInteractor implements LoginInputBoundary {

    final LoginUserDataAccessInterface loginUserDataAccessInterface;

    final LoginOutputBoundary userPresenter;

    public LoginInteractor(LoginUserDataAccessInterface loginUserDataAccessInterface, LoginOutputBoundary userPresenter) {
        this.loginUserDataAccessInterface = loginUserDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public LoginOutputData loginUser(LoginInputData loginInputData) {
        if (!loginUserDataAccessInterface.existsByName(loginInputData.getName())) {
            userPresenter.prepareFailView("User does not exists.");
        } else if (!loginUserDataAccessInterface.validatePassword(loginInputData.getName(), loginInputData.getPassword())) {
            userPresenter.prepareFailView("User password is Incorrect. Please try again");
        }

        User user = loginUserDataAccessInterface.getUserByName(loginInputData.getName());

        LocalDateTime now = LocalDateTime.now();
        LoginOutputData loginResponseModel = new LoginOutputData(user, now.toString());
        userPresenter.prepareSuccessView(loginResponseModel);

        return loginResponseModel;
    }
}
