package use_case.launch_menu.buttons;

/**
 * The {@code UserButtonsOutputBoundary} interface defines the contract for presenting the output
 * of user button interactions in the launch menu.
 */
public interface UserButtonsOutputBoundary {

    /**
     * Prepares to swap or present the output based on the provided {@code outputData}.
     *
     * <p>Implementations of this method should handle the presentation of the output, which may involve
     * swapping to a new view or performing other relevant actions based on the user's button interaction.</p>
     *
     * @param outputData The output data containing information to be presented or used for swapping views.
     */
    void prepareSwap(UserButtonsOutputData outputData);
}




