package use_case.launch_menu.login;

import entity.user.UserDataAccessFailed;

/**
 * The {@code LoginOutputBoundary} interface defines the contract for presenting the output
 * of user login interactions in the launch menu.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares to present a success view based on the provided {@code user}.
     *
     * <p>Implementations of this method should handle the presentation of the success view,
     * utilizing the information provided in the {@link LoginOutputData} parameter.</p>
     *
     * @param user The output data containing information about the logged-in user.
     */
    void prepareSuccessView(LoginOutputData user);

    /**
     * Prepares to present a fail view based on the provided {@code error}.
     *
     * <p>Implementations of this method should handle the presentation of the fail view,
     * utilizing the information provided in the {@link UserDataAccessFailed} parameter.</p>
     *
     * @param error The error data containing information about the failed login attempt.
     */
    void prepareFailView(UserDataAccessFailed error);
}