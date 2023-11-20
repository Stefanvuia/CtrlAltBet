package interface_adapter.blackjack.blackjack_start;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackStandViewModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackGameState;
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
        BlackJackGameState ingameStateBlackJack = blackJackHitViewModel.getState();
        ingameStateBlackJack.setUsername(outputData.getUser());
        ingameStateBlackJack.setBet(outputData.getBet());
        ingameStateBlackJack.setGame(outputData.getGame());
        ingameStateBlackJack.setGameEnd(false);

        this.blackJackHitViewModel.setState(ingameStateBlackJack);
        this.blackJackStandViewModel.setState(ingameStateBlackJack);

        this.viewManagerModel.setActiveView(blackJackHitViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        this.blackJackHitViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        BlackJackStartState errorGameState = blackJackStartViewModel.getState();
        errorGameState.setBetError(error);
        blackJackStartViewModel.setState(errorGameState);
        blackJackStartViewModel.firePropertyChanged();
    }
}
