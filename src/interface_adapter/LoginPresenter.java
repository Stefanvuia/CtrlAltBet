package interface_adapter;

import users.login.LoginOutputBoundary;
import users.login.LoginOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginPresenter implements LoginOutputBoundary {

    public void prepareSuccessView(LoginOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getLoginTime());
        response.setLoginTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    }

    public void prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}
