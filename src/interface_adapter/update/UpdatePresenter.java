package interface_adapter.update;

import entity.user.UserCreationFailed;
import use_case.launch_menu.update.UpdateOutputBoundary;

public class UpdatePresenter implements UpdateOutputBoundary {
    @Override
    public void prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}
