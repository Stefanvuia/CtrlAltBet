package interface_adapter.blackjack.blackjack_logic;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import use_case.blackjack.blackjack_logic.BlackJackOutputGameData;
import use_case.blackjack.blackjack_logic.BlackJackStandOutputBoundary;

public class BlackJackStandPresenter implements BlackJackStandOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;
    private ViewManagerModel viewManagerModel;

    public BlackJackStandPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   BlackJackStandViewModel blackJackStandViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareWinView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("You win!", outputGameData.getChange());
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Dealer hand wins!", outputGameData.getChange());
    }

    @Override
    public void preparePushView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Tie!", outputGameData.getChange());
    }

    private void gameFinishHelper(String message, int change) {
        BlackJackStartState newGameState = blackJackStartViewModel.getState();
        BlackJackGameState endingBlackJackGameState = blackJackStandViewModel.getState();

        endingBlackJackGameState.setGameMessage(message);
        endingBlackJackGameState.setGameEnd(true);

        newGameState.setUsername(endingBlackJackGameState.getUsername());
        newGameState.setBet(0);
        newGameState.setFunds(newGameState.getFunds() + change);
        newGameState.setBetError(null);

        this.blackJackStandViewModel.setState(endingBlackJackGameState);
        this.blackJackStandViewModel.firePropertyChanged();

        this.blackJackStartViewModel.setState(newGameState);
        this.blackJackStartViewModel.firePropertyChanged();


        this.viewManagerModel.setActiveView(blackJackStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
