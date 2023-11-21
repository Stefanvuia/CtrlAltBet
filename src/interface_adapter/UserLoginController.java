package interface_adapter;

import users.login.LoginInputBoundary;
import users.login.LoginInputData;
import users.login.LoginOutputData;

public class UserLoginController {

    final LoginInputBoundary userInput;

    public UserLoginController(LoginInputBoundary userInput) {
        this.userInput = userInput;
    }

    public LoginOutputData login(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        LoginOutputData user = userInput.loginUser(loginInputData);
        return user;
    }
}
