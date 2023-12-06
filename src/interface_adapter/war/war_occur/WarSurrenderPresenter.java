package interface_adapter.war.war_occur;

import interface_adapter.ViewManagerModel;
import interface_adapter.war.WarGameState;
import interface_adapter.war.war_start.WarStartState;
import interface_adapter.war.war_start.WarStartViewModel;
import use_case.games.war.war_logic.WarOutputGameData;
import use_case.games.war.war_logic.WarSurrenderOutputBoundary;

/**
 * The WarSurrenderPresenter class implements the WarSurrenderOutputBoundary interface and is
 * responsible for preparing the presentation logic related to the surrender action in the War
 * card game. It collaborates with the WarStartViewModel, WarOccurViewModel, and ViewManagerModel
 * to update the UI components and handle various game states and outcomes.
 */
public class WarSurrenderPresenter implements WarSurrenderOutputBoundary {

    /** The ViewModel for the start state of the War card game. */
    private final WarStartViewModel warStartViewModel;

    /** The ViewModel for the when a war occurs of the War card game. */
    private final WarOccurViewModel warOccurViewModel;

    /** The model managing the active views in the application. */
    private final ViewManagerModel viewManagerModel;
    public WarSurrenderPresenter(WarStartViewModel warStartViewModel,
                                 ViewManagerModel viewManagerModel,
                                 WarOccurViewModel warOccurViewModel) {
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.warOccurViewModel = warOccurViewModel;
    }

    /**
     * Prepares the surrender view based on the provided WarOutputGameData, updating the UI
     * components and managing game state accordingly.
     *
     * @param warOutputGameData The data containing the outcome of the surrender action.
     */
    @Override
    public void prepareSurrenderView(WarOutputGameData warOutputGameData) {
        WarGameState currentState = warOccurViewModel.getState();
        WarStartState newGameState = warStartViewModel.getState();

        // Update game state to indicate surrender
        currentState.setSurrendered(true);

        // Notify listeners of the state change
        this.warOccurViewModel.firePropertyChanged();

        // Update funds and reset bet-related values
        newGameState.setFunds(newGameState.getFunds() - newGameState.getBet() + newGameState.getBet() / 2);
        newGameState.setBet(0);
        newGameState.setBetError(null);

        // Update the start state ViewModel and notify listeners
        this.warStartViewModel.setState(newGameState);
        this.warStartViewModel.firePropertyChanged();

        // Set the active view and notify listeners
        this.viewManagerModel.setActiveView(warStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
