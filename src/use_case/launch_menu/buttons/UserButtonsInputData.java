package use_case.launch_menu.buttons;

/**
 * The {@code UserButtonsInputData} class represents the input data for user interactions with buttons
 * in the launch menu.
 *
 * <p>Instances of this class encapsulate information about the user's interaction, specifically the new view
 * associated with the button press.</p>
 *
 * <p>This class provides methods to retrieve the new view information through the {@code getNewView} method.</p>
 */
public class UserButtonsInputData {

    /**
     * The new view associated with the user's interaction.
     */
    private String newView = "";

    /**
     * Constructs a {@code UserButtonsInputData} instance with the specified new view.
     *
     * @param newView The new view associated with the user's interaction.
     */
    public UserButtonsInputData(String newView) {
        this.newView = newView;
    }

    /**
     * Constructs a default {@code UserButtonsInputData} instance with an empty new view.
     */
    public UserButtonsInputData() {}

    /**
     * Retrieves the new view associated with the user's interaction.
     *
     * @return The new view.
     */
    public String getNewView() {
        return newView;
    }
}