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
     * Constructs a {@code LoginOutputData} instance with the specified {@code user} and {@code loginTime}.
     *
     * @param user The user object representing the logged-in user.
     */
    public LoginOutputData(User user) {
        this.user = user;
    }

    /**
     * Retrieves the user object representing the logged-in user.
     *
     * @return The user object.
     */
    public User getUser() {
        return user;
    }
}