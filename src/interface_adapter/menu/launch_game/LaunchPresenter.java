package interface_adapter.menu.launch_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.baccarat.BaccaratStartState;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import use_case.menu.launch_game.LaunchOutputBoundary;
import use_case.menu.launch_game.LaunchOutputData;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;

public class LaunchPresenter implements LaunchOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BaccaratStartViewModel baccaratStartViewModel;

    final ViewManagerModel viewManagerModel;

    public LaunchPresenter(BlackJackStartViewModel blackJackStartViewModel,
                           BaccaratStartViewModel baccaratStartViewModel,
                           ViewManagerModel viewManagerModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.baccaratStartViewModel = baccaratStartViewModel;
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
        }
    }
}
