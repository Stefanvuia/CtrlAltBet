package interface_adapter.launch_menu.login;

import use_case.launch_menu.login.LoginInputBoundary;
import use_case.launch_menu.login.LoginInputData;

public class UserLoginController {

    final LoginInputBoundary userInput;

    public UserLoginController(LoginInputBoundary userInput) {
        this.userInput = userInput;
    }

    public void login(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        userInput.loginUser(loginInputData);
    }
}
