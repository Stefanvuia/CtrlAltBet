package interface_adapter.launch_menu.sign_up;

/**
 * Represents the state of the sign-up view in the launch menu.
 * Contains information such as any error message and whether a user has been successfully created.
 */
public class SignupState {

    /**
     * Any error message related to the sign-up process.
     */
    private String error = null;

    /**
     * A flag indicating whether a user has been successfully created.
     */
    private boolean userCreated = false;

    /**
     * Gets the error message related to the sign-up process.
     *
     * @return The error message, or null if no error.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message for the sign-up state.
     *
     * @param error The error message to set.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Checks if a user has been successfully created.
     *
     * @return true if a user has been created, false otherwise.
     */
    public boolean isUserCreated() {
        return userCreated;
    }

    /**
     * Sets the user creation status for the sign-up state.
     *
     * @param userCreated true if a user has been created, false otherwise.
     */
    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }
}