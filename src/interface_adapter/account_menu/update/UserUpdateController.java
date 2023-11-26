package interface_adapter.account_menu.update;

import use_case.account_menu.update.UpdateInputBoundary;
import use_case.account_menu.update.UpdateInputData;

public class UserUpdateController {

    final UpdateInputBoundary userGateway;

    public UserUpdateController(UpdateInputBoundary accountGateway) {
        this.userGateway = accountGateway;
    }

    public void updateUser(String username, int balance) {
        UpdateInputData updateInputData = new UpdateInputData(username, balance);
        userGateway.updateUser(updateInputData);
    }
}
