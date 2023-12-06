package interface_adapter.war.war_occur;

import interface_adapter.ViewManagerModel;
import interface_adapter.war.WarGameState;
import interface_adapter.war.war_start.WarStartState;
import interface_adapter.war.war_start.WarStartViewModel;
import use_case.games.war.war_logic.WarOutputGameData;
import use_case.games.war.war_logic.WarSurrenderOutputBoundary;

public class WarSurrenderPresenter implements WarSurrenderOutputBoundary {
    private final WarStartViewModel warStartViewModel;
    private final WarOccurViewModel warOccurViewModel;
    private final ViewManagerModel viewManagerModel;

    public WarSurrenderPresenter(WarStartViewModel warStartViewModel,
                                 ViewManagerModel viewManagerModel,
                                 WarOccurViewModel warOccurViewModel) {
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.warOccurViewModel = warOccurViewModel;
    }

    @Override
    public void prepareSurrenderView(WarOutputGameData warOutputGameData) {
        WarGameState currentState = warOccurViewModel.getState();
        WarStartState newGameState = warStartViewModel.getState();
        currentState.setSurrendered(true);

        this.warOccurViewModel.firePropertyChanged();
        newGameState.setFunds(newGameState.getFunds() - newGameState.getBet() / 2);
        newGameState.setBet(0);
        newGameState.setBetError(null);

        this.warStartViewModel.setState(newGameState);
        this.warStartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(warStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
