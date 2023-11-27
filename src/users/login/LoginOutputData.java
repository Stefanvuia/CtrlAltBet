package users.login;

import entity.user.User;

// Sign up response body
public class LoginOutputData {

    private final User user;
    private String loginTime;

    public LoginOutputData(User user, String loginTime) {
        this.user = user;
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

}
