package use_case.launch_menu.signup;

/**
 * The {@code SignupOutputData} class represents the output data for user signup interactions in the launch menu.
 *
 * <p>Instances of this class encapsulate information about the newly created user and the time of creation.</p>
 */
public class SignupOutputData {

    /**
     * The username of the newly created user.
     */
    private final String user;

    /**
     * The time at which the user was created.
     */
    private String creationTime;

    /**
     * Constructs a {@code SignupOutputData} instance with the specified {@code user} and {@code creationTime}.
     *
     * @param user The username of the newly created user.
     * @param creationTime The time at which the user was created.
     */
    public SignupOutputData(String user, String creationTime) {
        this.user = user;
        this.creationTime = creationTime;
    }

    /**
     * Retrieves the username of the newly created user.
     *
     * @return The username.
     */
    public String getUser() {
        return user;
    }

    /**
     * Retrieves the time at which the user was created.
     *
     * @return The creation time.
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the creation time to the specified value.
     *
     * @param creationTime The new creation time.
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}