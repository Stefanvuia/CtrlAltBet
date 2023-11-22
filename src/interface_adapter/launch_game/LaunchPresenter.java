package interface_adapter.launch_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import use_case.launch_game.LaunchOutputBoundary;
import use_case.launch_game.LaunchOutputData;

public class LaunchPresenter implements LaunchOutputBoundary {
    private BlackJackStartViewModel blackJackStartViewModel;

    private BaccaratStartViewModel baccaratStartViewModel;

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

        } else if (gameName.equals(baccaratStartViewModel.getViewName())) {

        }
    }
}
