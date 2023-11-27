package interface_adapter.launch_menu.sign_up;

import use_case.launch_menu.signup.SignupInputBoundary;
import use_case.launch_menu.signup.SignupInputData;

public class UserSignupController {

    final SignupInputBoundary userInput;

    public UserSignupController(SignupInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    public void create(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userInput.createUser(signupInputData);
    }
}
