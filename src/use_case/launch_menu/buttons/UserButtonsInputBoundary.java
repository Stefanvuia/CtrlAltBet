package use_case.launch_menu.buttons;

/**
 * The {@code UserButtonsInputBoundary} interface defines the contract for handling user input related to buttons
 * in the launch menu.
 *
 * <p>Implementations of this interface are responsible for executing the specified user button input and
 * processing it accordingly. The input data required for the execution is provided through the {@link UserButtonsInputData}
 * parameter.</p>
 *
 * <p>Classes that implement this interface should encapsulate the logic associated with handling user input for
 * buttons in the launch menu.</p>
 *
 * @see UserButtonsInputData
 */
public interface UserButtonsInputBoundary {

    /**
     * Executes the specified user button input based on the provided {@code inputData}.
     *
     * <p>Implementations of this method should process the input data and perform the necessary actions
     * related to the user's interaction with buttons in the launch menu.</p>
     *
     * @param inputData The input data containing information about the user's interaction with buttons.
     */
    void execute(UserButtonsInputData inputData);
}