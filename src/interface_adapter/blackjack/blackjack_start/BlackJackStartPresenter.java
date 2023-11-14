package interface_adapter.blackjack.blackjack_start;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.GameState;
import interface_adapter.blackjack.blackjack_logic.BlackJackIngameViewModel;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartOutputData;

public class BlackJackStartPresenter  implements BlackJackStartOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;
    private final BlackJackIngameViewModel blackJackIngameViewModel;

    private ViewManagerModel viewManagerModel;

    public BlackJackStartPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   BlackJackIngameViewModel blackJackIngameViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackIngameViewModel = blackJackIngameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(BlackJackStartOutputData outputData) {
        GameState ingameState = blackJackIngameViewModel.getState();
        ingameState.setUsername(outputData.getUser());
        ingameState.setBet(outputData.getBet());
        ingameState.setGame(outputData.getGame());

        this.blackJackIngameViewModel.setState(ingameState);
        this.blackJackIngameViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(blackJackStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        StartState errorGameState = blackJackStartViewModel.getState();
        errorGameState.setBetError(error);
        blackJackStartViewModel.firePropertyChanged();
    }
}
