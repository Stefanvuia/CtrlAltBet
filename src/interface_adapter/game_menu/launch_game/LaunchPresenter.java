package interface_adapter.game_menu.launch_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.baccarat.BaccaratStartState;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.war.war_start.WarStartState;
import interface_adapter.war.war_start.WarStartViewModel;
import use_case.game_menu.launch_game.LaunchOutputBoundary;
import use_case.game_menu.launch_game.LaunchOutputData;

public class LaunchPresenter implements LaunchOutputBoundary {
    final ViewManagerModel viewManagerModel;
    private final BlackJackStartViewModel blackJackStartViewModel;
    private final BaccaratStartViewModel baccaratStartViewModel;
    private final WarStartViewModel warStartViewModel;

    public LaunchPresenter(BlackJackStartViewModel blackJackStartViewModel,
                           BaccaratStartViewModel baccaratStartViewModel,
                           WarStartViewModel warStartViewModel, ViewManagerModel viewManagerModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.baccaratStartViewModel = baccaratStartViewModel;
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareGameView(LaunchOutputData outputData) {
        String gameName = outputData.getGame();
        String username = outputData.getUsername();
        int initFunds = outputData.getFunds();

        if (gameName.equals(blackJackStartViewModel.getViewName())) {
            BlackJackStartState blackJackStartState = new BlackJackStartState();

            blackJackStartState.setUsername(username);
            blackJackStartState.setFunds(initFunds);

            blackJackStartViewModel.setState(blackJackStartState);
            blackJackStartViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(blackJackStartViewModel.getViewName());
            viewManagerModel.firePropertyChanged();

        } else if (gameName.equals(baccaratStartViewModel.getViewName())) {
            BaccaratStartState baccaratStartState = new BaccaratStartState();

            baccaratStartState.setUsername(username);
            baccaratStartState.setFund(initFunds);

            baccaratStartViewModel.setState(baccaratStartState);
            baccaratStartViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(baccaratStartViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (gameName.equals(warStartViewModel.getViewName())) {
            WarStartState warStartState = new WarStartState();

            warStartState.setUsername(username);
            warStartState.setFunds(initFunds);

            warStartViewModel.setState(warStartState);
            warStartViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(warStartViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }
}
