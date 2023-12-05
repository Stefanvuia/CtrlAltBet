package use_case.launch_menu.login;

/**
 * The {@code LoginInputData} class represents the input data for user login in the launch menu.
 *
 * <p>Instances of this class encapsulate information about the user's name and password provided during login.</p>
 */
public class LoginInputData {

    /**
     * The user's name provided during login.
     */
    private final String name;

    /**
     * The password provided during login.
     */
    private final String password;

    /**
     * Constructs a {@code LoginInputData} instance with the specified user name and password.
     *
     * @param name The user's name provided during login.
     * @param password The password provided during login.
     */
    public LoginInputData(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Retrieves the user's name provided during login.
     *
     * @return The user's name.
     */
    String getName() {
        return name;
    }

    /**
     * Retrieves the password provided during login.
     *
     * @return The password.
     */
    String getPassword() {
        return password;
    }
}




