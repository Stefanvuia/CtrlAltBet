package interface_adapter.account_menu.update;

import use_case.account_menu.update.UpdateInputBoundary;
import use_case.account_menu.update.UpdateInputData;

/**
 * Controller class responsible for handling user update operations.
 * Bridges the communication between the user interface and the update use case.
 */
public class UserUpdateController {

    /**
     * The gateway for the update use case, implementing the UpdateInputBoundary.
     */
    final UpdateInputBoundary userGateway;

    /**
     * Constructs a new UserUpdateController with the specified UpdateInputBoundary.
     *
     * @param accountGateway The gateway for the update use case.
     */
    public UserUpdateController(UpdateInputBoundary accountGateway) {
        this.userGateway = accountGateway;
    }

    /**
     * Initiates the process of updating user information with the specified username and balance.
     * Creates an UpdateInputData object and invokes the updateUser method of the associated gateway.
     *
     * @param username The username of the user to be updated.
     * @param balance  The new balance to be set for the user.
     */
    public void updateUser(String username, int balance) {
        UpdateInputData updateInputData = new UpdateInputData(username, balance);
        userGateway.updateUser(updateInputData);
    }
}
