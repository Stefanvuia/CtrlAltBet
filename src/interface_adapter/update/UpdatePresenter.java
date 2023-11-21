package interface_adapter.update;

import interface_adapter.UserCreationFailed;
import users.update.UpdateOutputBoundary;

public class UpdatePresenter implements UpdateOutputBoundary {
    @Override
    public void prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}
