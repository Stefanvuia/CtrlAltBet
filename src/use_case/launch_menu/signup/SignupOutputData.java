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
     * Constructs a {@code SignupOutputData} instance with the specified {@code user} and {@code creationTime}.
     *
     * @param user The username of the newly created user.
     */
    public SignupOutputData(String user) {
        this.user = user;
    }

    /**
     * Retrieves the username of the newly created user.
     *
     * @return The username.
     */
    public String getUser() {
        return user;
    }
}