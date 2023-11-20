package interface_adapter.blackjack.blackjack_start;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackStandViewModel;
import interface_adapter.blackjack.blackjack_logic.GameState;
import interface_adapter.blackjack.blackjack_logic.BlackJackHitViewModel;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartOutputData;

public class BlackJackStartPresenter  implements BlackJackStartOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;
    private final BlackJackHitViewModel blackJackHitViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;

    private final ViewManagerModel viewManagerModel;

    public BlackJackStartPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   BlackJackHitViewModel blackJackHitViewModel,
                                   ViewManagerModel viewManagerModel,
                                   BlackJackStandViewModel blackJackStandViewModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackHitViewModel = blackJackHitViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
    }

    @Override
    public void prepareSuccessView(BlackJackStartOutputData outputData) {
        GameState ingameState = blackJackHitViewModel.getState();
        ingameState.setUsername(outputData.getUser());
        ingameState.setBet(outputData.getBet());
        ingameState.setGame(outputData.getGame());
        ingameState.setGameEnd(false);

        this.blackJackHitViewModel.setState(ingameState);
        this.blackJackStandViewModel.setState(ingameState);

        this.viewManagerModel.setActiveView(blackJackHitViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        this.blackJackHitViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        StartState errorGameState = blackJackStartViewModel.getState();
        errorGameState.setBetError(error);
        blackJackStartViewModel.firePropertyChanged();
    }
}
