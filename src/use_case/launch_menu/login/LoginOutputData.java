package use_case.launch_menu.login;

import entity.user.User;

/**
 * The {@code LoginOutputData} class represents the output data for user login interactions in the launch menu.
 *
 * <p>Instances of this class encapsulate information about the logged-in user and the time of login.</p>
 */
public class LoginOutputData {

    /**
     * The user object representing the logged-in user.
     */
    private final User user;

    /**
     * The time at which the user logged in.
     */
    private String loginTime;

    /**
     * Constructs a {@code LoginOutputData} instance with the specified {@code user} and {@code loginTime}.
     *
     * @param user The user object representing the logged-in user.
     * @param loginTime The time at which the user logged in.
     */
    public LoginOutputData(User user, String loginTime) {
        this.user = user;
        this.loginTime = loginTime;
    }

    /**
     * Retrieves the user object representing the logged-in user.
     *
     * @return The user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Retrieves the time at which the user logged in.
     *
     * @return The login time.
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * Sets the login time to the specified value.
     *
     * @param loginTime The new login time.
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}