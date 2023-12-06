package interface_adapter.launch_menu.sign_up;

import use_case.launch_menu.signup.SignupInputBoundary;
import use_case.launch_menu.signup.SignupInputData;

/**
 * Controller class responsible for handling user sign-up interactions in the launch menu.
 */
public class UserSignupController {

    /**
     * The input boundary for the sign-up use case.
     */
    final SignupInputBoundary userInput;

    /**
     * Creates a new instance of UserSignupController.
     *
     * @param accountGateway The input boundary for the sign-up use case.
     */
    public UserSignupController(SignupInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    /**
     * Initiates the user sign-up process with the provided username and passwords.
     *
     * @param username  The username entered by the user.
     * @param password1 The first password entered by the user.
     * @param password2 The second password entered by the user for confirmation.
     */
    public void create(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userInput.createUser(signupInputData);
    }
}