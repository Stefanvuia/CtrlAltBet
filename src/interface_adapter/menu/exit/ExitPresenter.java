package interface_adapter.menu.exit;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.launch_game.LaunchViewModel;
import use_case.menu.exit.ExitOutputBoundary;

public class ExitPresenter implements ExitOutputBoundary {
    private final LaunchViewModel launchViewModel;

    final ViewManagerModel viewManagerModel;

    public ExitPresenter(LaunchViewModel launchViewModel,
                         ViewManagerModel viewManagerModel) {
        this.launchViewModel = launchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSwapView() {
        viewManagerModel.setActiveView(launchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
