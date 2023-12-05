package use_case.launch_menu.signup;

/**
 * The {@code SignupInputBoundary} interface defines the contract for handling user signup input
 * in the launch menu.
 */
public interface SignupInputBoundary {

    /**
     * Initiates the process of creating a new user based on the provided {@code signupInputData}.
     *
     * <p>Implementations of this method should handle the logic associated with creating a new user,
     * utilizing the information provided in the {@link SignupInputData} parameter.</p>
     *
     * @param signupInputData The input data containing information necessary for user signup.
     */
    void createUser(SignupInputData signupInputData);
}