package use_case.launch_menu.signup;

import entity.user.User;

/**
 * The {@code SignupUserDataAccessInterface} interface defines the contract for accessing user data
 * during the signup process in the launch menu.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if a user with the specified identifier (e.g., name) already exists.
     *
     * @param identifier The identifier (e.g., name) of the user.
     * @return {@code true} if a user with the specified identifier already exists, {@code false} otherwise.
     */
    boolean existsByName(String identifier);

    /**
     * Saves the user information provided in the {@code requestModel}.
     *
     * @param requestModel The user object containing information to be saved.
     */
    void save(User requestModel);
}