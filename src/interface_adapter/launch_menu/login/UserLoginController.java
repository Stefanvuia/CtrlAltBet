package interface_adapter.launch_menu.login;

import use_case.launch_menu.login.LoginInputBoundary;
import use_case.launch_menu.login.LoginInputData;

/**
 * Controller class responsible for handling user login interactions in the launch menu.
 */
public class UserLoginController {

    /**
     * The input boundary for the login use case.
     */
    private final LoginInputBoundary userInput;

    /**
     * Creates a new instance of UserLoginController.
     *
     * @param userInput The input boundary for the login use case.
     */
    public UserLoginController(LoginInputBoundary userInput) {
        this.userInput = userInput;
    }

    /**
     * Initiates the user login process with the provided username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void login(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        userInput.loginUser(loginInputData);
    }
}
