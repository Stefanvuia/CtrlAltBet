package use_case.launch_menu.login;

import entity.user.User;
import entity.user.UserDataAccessFailed;

import java.time.LocalDateTime;

/**
 * The {@code LoginInteractor} class implements the {@code LoginInputBoundary} interface and is responsible
 * for handling user login interactions in the launch menu.
 */
public class LoginInteractor implements LoginInputBoundary {

    /**
     * The interface for accessing user data during login.
     */
    private final LoginUserDataAccessInterface loginUserDataAccessInterface;

    /**
     * The presenter responsible for handling the output of user login interactions.
     */
    private final LoginOutputBoundary userPresenter;

    /**
     * Constructs a {@code LoginInteractor} with the specified {@code loginUserDataAccessInterface}
     * and {@code userPresenter}.
     *
     * @param loginUserDataAccessInterface The interface for accessing user data during login.
     * @param userPresenter The presenter responsible for handling the output of user login interactions.
     */
    public LoginInteractor(LoginUserDataAccessInterface loginUserDataAccessInterface, LoginOutputBoundary userPresenter) {
        this.loginUserDataAccessInterface = loginUserDataAccessInterface;
        this.userPresenter = userPresenter;
    }

    /**
     * Initiates the process of logging in a user based on the provided {@code loginInputData}.
     *
     * <p>If the user does not exist, a fail view is prepared with an appropriate message. If the password
     * is incorrect, another fail view is prepared. Otherwise, a success view is prepared with user information.</p>
     *
     * @param loginInputData The input data containing information necessary for user login.
     */
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




