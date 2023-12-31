package interface_adapter.game_menu.exit;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_menu.launch_game.LaunchViewModel;
import use_case.game_menu.exit.ExitOutputBoundary;

public class ExitPresenter implements ExitOutputBoundary {
    final ViewManagerModel viewManagerModel;
    private final LaunchViewModel launchViewModel;

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
