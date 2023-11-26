package use_case.launch_menu.login;

import entity.user.User;
import entity.user.UserDataAccessFailed;

import java.time.LocalDateTime;

public class LoginInteractor implements LoginInputBoundary {

    final LoginUserDataAccessInterface loginUserDataAccessInterface;

    final LoginOutputBoundary userPresenter;

    public LoginInteractor(LoginUserDataAccessInterface loginUserDataAccessInterface, LoginOutputBoundary userPresenter) {
        this.loginUserDataAccessInterface = loginUserDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    @Override
    public void loginUser(LoginInputData loginInputData) {
        if (!loginUserDataAccessInterface.existsByName(loginInputData.getName())) {
            userPresenter.prepareFailView(new UserDataAccessFailed("User does not exist"));
        } else if (!loginUserDataAccessInterface.validatePassword(loginInputData.getName(), loginInputData.getPassword())) {
            userPresenter.prepareFailView(new UserDataAccessFailed("User password is Incorrect. Please try again"));
        } else {
            User user = loginUserDataAccessInterface.getUserByName(loginInputData.getName());

            LocalDateTime now = LocalDateTime.now();
            LoginOutputData loginResponseModel = new LoginOutputData(user, now.toString());
            userPresenter.prepareSuccessView(loginResponseModel);
        }
    }
}
