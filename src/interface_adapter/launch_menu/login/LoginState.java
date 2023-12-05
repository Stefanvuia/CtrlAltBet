package interface_adapter.launch_menu.login;

/**
 * Represents the state of the login view in the launch menu.
 * Contains information such as the entered username and any error message.
 */
public class LoginState {

    /**
     * The username entered by the user.
     */
    private String username = "";

    /**
     * Any error message related to the login process.
     */
    private String error = null;

    /**
     * Gets the username entered by the user.
     *
     * @return The entered username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the login state.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the error message related to the login process.
     *
     * @return The error message, or null if no error.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message for the login state.
     *
     * @param error The error message to set.
     */
    public void setError(String error) {
        this.error = error;
    }
}