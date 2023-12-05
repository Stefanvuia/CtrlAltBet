package use_case.launch_menu.login;

import entity.user.User;

/**
 * The {@code LoginUserDataAccessInterface} interface defines the contract for accessing user data
 * during the login process in the launch menu.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if a user with the specified identifier (e.g., name) exists.
     *
     * @param identifier The identifier (e.g., name) of the user.
     * @return {@code true} if a user with the specified identifier exists, {@code false} otherwise.
     */
    boolean existsByName(String identifier);

    /**
     * Retrieves the user object with the specified username.
     *
     * @param username The username of the user.
     * @return The user object corresponding to the specified username.
     */
    User getUserByName(String username);

    /**
     * Validates the password for a user with the specified identifier (e.g., name).
     *
     * @param identifier The identifier (e.g., name) of the user.
     * @param password The password to validate.
     * @return {@code true} if the password is valid for the user with the specified identifier,
     *         {@code false} otherwise.
     */
    boolean validatePassword(String identifier, String password);
}




