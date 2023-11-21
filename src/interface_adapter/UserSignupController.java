package interface_adapter;

import users.SignupInputBoundary;
import users.SignupInputData;

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
