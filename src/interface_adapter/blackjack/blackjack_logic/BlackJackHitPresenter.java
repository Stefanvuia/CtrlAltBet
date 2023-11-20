package interface_adapter.blackjack.blackjack_logic;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import use_case.blackjack.blackjack_logic.BlackJackHitOutputBoundary;
import use_case.blackjack.blackjack_logic.BlackJackOutputGameData;

public class BlackJackHitPresenter implements BlackJackHitOutputBoundary {
    private final BlackJackHitViewModel blackJackHitViewModel;
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;
    private ViewManagerModel viewManagerModel;

    public BlackJackHitPresenter(BlackJackHitViewModel blackJackHitViewModel,
                                 BlackJackStartViewModel blackJackStartViewModel,
                                 ViewManagerModel viewManagerModel,
                                 BlackJackStandViewModel blackJackStandViewModel) {
        this.blackJackHitViewModel = blackJackHitViewModel;
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
    }

    @Override
    public void prepareContinueView(BlackJackOutputGameData outputGameData) {
        BlackJackGameState currentBlackJackGameState = blackJackHitViewModel.getState();
        currentBlackJackGameState.setGame(outputGameData.getGame());

        this.blackJackHitViewModel.setState(currentBlackJackGameState);
        this.blackJackStandViewModel.setState(currentBlackJackGameState);

        this.blackJackHitViewModel.firePropertyChanged();
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        BlackJackStartState newGameState = blackJackStartViewModel.getState();
        BlackJackGameState endingBlackJackGameState = blackJackHitViewModel.getState();

        endingBlackJackGameState.setGameMessage("You bust!");
        endingBlackJackGameState.setGameEnd(true);

        newGameState.setUsername(endingBlackJackGameState.getUsername());
        newGameState.setBet(0);
        newGameState.setFunds(newGameState.getFunds() + outputGameData.getChange());
        newGameState.setBetError(null);

        this.blackJackHitViewModel.setState(endingBlackJackGameState);
        this.blackJackHitViewModel.firePropertyChanged();

        this.blackJackStartViewModel.setState(newGameState);
        this.blackJackStartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(blackJackStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
