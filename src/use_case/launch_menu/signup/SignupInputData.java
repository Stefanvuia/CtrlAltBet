package use_case.launch_menu.signup;

/**
 * The {@code SignupInputData} class represents the input data for user signup in the launch menu.
 *
 * <p>Instances of this class encapsulate information about the user's name, password, and repeated password
 * provided during signup.</p>
 */
public class SignupInputData {

    /**
     * The user's name provided during signup.
     */
    private final String name;

    /**
     * The password provided during signup.
     */
    private final String password;

    /**
     * The repeated password provided during signup for confirmation.
     */
    private final String repeatPassword;

    /**
     * Constructs a {@code SignupInputData} instance with the specified user name, password, and repeated password.
     *
     * @param name The user's name provided during signup.
     * @param password The password provided during signup.
     * @param repeatPassword The repeated password provided during signup for confirmation.
     */
    public SignupInputData(String name, String password, String repeatPassword) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Retrieves the user's name provided during signup.
     *
     * @return The user's name.
     */
    String getName() {
        return name;
    }

    /**
     * Retrieves the password provided during signup.
     *
     * @return The password.
     */
    String getPassword() {
        return password;
    }

    /**
     * Retrieves the repeated password provided during signup for confirmation.
     *
     * @return The repeated password.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
}