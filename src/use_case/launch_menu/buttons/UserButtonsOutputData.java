package use_case.launch_menu.buttons;

/**
 * The {@code UserButtonsOutputData} class represents the output data for user button interactions in the launch menu.
 *
 * <p>Instances of this class encapsulate information about the new view associated with the output of a user's button interaction.</p>
 */
public class UserButtonsOutputData {

    /**
     * The new view associated with the user's button interaction output.
     */
    private final String newView;

    /**
     * Constructs a {@code UserButtonsOutputData} instance with the specified new view.
     *
     * @param newView The new view associated with the user's button interaction output.
     */
    public UserButtonsOutputData(String newView) {
        this.newView = newView;
    }

    /**
     * Retrieves the new view associated with the user's button interaction output.
     *
     * @return The new view.
     */
    public String getNewView() {
        return newView;
    }
}