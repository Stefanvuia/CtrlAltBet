package use_case.launch_menu.signup;

import entity.user.UserDataAccessFailed;

/**
 * The {@code SignupOutputBoundary} interface defines the contract for presenting the output
 * of user signup interactions in the launch menu.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares to present a success view based on the provided {@code user}.
     *
     * <p>Implementations of this method should handle the presentation of the success view,
     * utilizing the information provided in the {@link SignupOutputData} parameter.</p>
     *
     * @param user The output data containing information about the newly created user.
     */
    void prepareSuccessView(SignupOutputData user);

    /**
     * Prepares to present a fail view based on the provided {@code error}.
     *
     * <p>Implementations of this method should handle the presentation of the fail view,
     * utilizing the information provided in the {@link UserDataAccessFailed} parameter.</p>
     *
     * @param error The error data containing information about the failed signup attempt.
     */
    void prepareFailView(UserDataAccessFailed error);
}