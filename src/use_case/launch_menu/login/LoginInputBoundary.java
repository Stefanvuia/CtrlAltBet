package use_case.launch_menu.login;

/**
 * The {@code LoginInputBoundary} interface defines the contract for handling user login input
 * in the launch menu.
 */
public interface LoginInputBoundary {

    /**
     * Initiates the process of logging in a user based on the provided {@code loginInputData}.
     *
     * <p>Implementations of this method should handle the logic associated with user login, utilizing
     * the information provided in the {@link LoginInputData} parameter.</p>
     *
     * @param loginInputData The input data containing information necessary for user login.
     */
    void loginUser(LoginInputData loginInputData);
}