package interface_adapter.update;

import users.update.UpdateInputBoundary;
import users.update.UpdateInputData;

import java.math.BigDecimal;

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
