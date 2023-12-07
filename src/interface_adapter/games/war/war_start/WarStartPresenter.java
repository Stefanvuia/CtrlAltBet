package interface_adapter.games.war.war_start;

import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.games.war.WarGameState;
import interface_adapter.games.war.war_logic.WarIngameViewModel;
import interface_adapter.games.war.war_occur.WarOccurViewModel;
import use_case.games.war.war_started.WarStartOutputBoundary;
import use_case.games.war.war_started.WarStartOutputData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The WarStartPresenter class implements the WarStartOutputBoundary interface and is responsible
 * for preparing the presentation logic related to the start and outcome views in the War card game.
 * It collaborates with the WarStartViewModel, ViewManagerModel, WarIngameViewModel, and WarOccurViewModel
 * to update the UI components and handle various game states and outcomes.
 */
public class WarStartPresenter implements WarStartOutputBoundary {

    /**
     * The ViewModel for the start state of the War card game.
     */
    private final WarStartViewModel warStartViewModel;
    /**
     * The model managing the active views in the application.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * The ViewModel for the in-game state of the War card game.
     */
    private final WarIngameViewModel warIngameViewModel;

    /**
     * The ViewModel for the occurrence state of the War card game.
     */
    private final WarOccurViewModel warOccurViewModel;

    /**
     * Image factory the makes images for cards
     */
    private final ImageFactory imageFactory = new CardImageFactory();

    /**
     * Constructs a new WarStartPresenter with the specified ViewModels and ViewManagerModel.
     *
     * @param warStartViewModel  The ViewModel for the start state of the War card game.
     * @param viewManagerModel   The model managing the active views in the application.
     * @param warIngameViewModel The ViewModel for the in-game state of the War card game.
     * @param warOccurViewModel  The ViewModel for the occurrence state of the War card game.
     */

    public WarStartPresenter(WarStartViewModel warStartViewModel,
                             ViewManagerModel viewManagerModel,
                             WarIngameViewModel warIngameViewModel,
                             WarOccurViewModel warOccurViewModel) {
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.warIngameViewModel = warIngameViewModel;
        this.warOccurViewModel = warOccurViewModel;
    }

    /**
     * Prepares the in-game view based on the provided WarStartOutputData, updating the UI components
     * and managing game state accordingly.
     *
     * @param outputData The data containing the outcome of the start action.
     */
    @Override
    public void prepareWarIngameView(WarStartOutputData outputData) {
        WarGameState ingameStateWar = new WarGameState();
        WarStartState newGameState = warStartViewModel.getState();

        // Populate in-game state with relevant data
        ingameStateWar.setBet(outputData.getBet());
        ingameStateWar.setGame(outputData.getGame());
        ingameStateWar.setPlayerImages(makeImages(outputData.getGame().getPlayer().getHand()));
        ingameStateWar.setDealerImages(makeImages(outputData.getGame().getDealer().getHand()));

        // Update the in-game ViewModel and set the active view
        this.warIngameViewModel.setState(ingameStateWar);
        this.viewManagerModel.setActiveView(warIngameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        // Notify listeners of the in-game state change
        this.warIngameViewModel.firePropertyChanged();

        // Update funds based on game outcome
        if (outputData.getGame().playerWins()) {
            newGameState.setFunds(newGameState.getFunds() + newGameState.getBet());
        } else {
            newGameState.setFunds(newGameState.getFunds() - newGameState.getBet());
        }

        // Reset bet-related values
        newGameState.setBet(0);
        newGameState.setBetError(null);

        // Update the start state ViewModel and notify listeners
        this.warStartViewModel.setState(newGameState);
        this.warStartViewModel.firePropertyChanged();

        // Set the active view to the start state and notify listeners
        this.viewManagerModel.setActiveView(warStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the "Go to War" view based on the provided WarStartOutputData, updating the UI components
     * and managing game state accordingly.
     *
     * @param outputData The data containing the outcome of the start action.
     */
    @Override
    public void prepareGoToWarView(WarStartOutputData outputData) {
        WarGameState ingameStateWar = new WarGameState();

        // Populate in-game state with relevant data
        ingameStateWar.setBet(outputData.getBet());
        ingameStateWar.setGame(outputData.getGame());
        ingameStateWar.setPlayerImages(makeImages(outputData.getGame().getPlayer().getHand()));
        ingameStateWar.setDealerImages(makeImages(outputData.getGame().getDealer().getHand()));

        // Update the occurrence state ViewModel and set the active view
        this.warOccurViewModel.setState(ingameStateWar);
        this.viewManagerModel.setActiveView(warOccurViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        // Update the occurrence state ViewModel and set the active view
        this.warOccurViewModel.firePropertyChanged();


    }

    /**
     * Prepares the fail view by updating the UI components with the provided error message.
     *
     * @param error The error message to display in the fail view.
     */
    @Override
    public void prepareFailView(String error) {
        WarStartState errorGameState = warStartViewModel.getState();

        // Set the error message in the start state ViewModel
        errorGameState.setBetError(error);
        warStartViewModel.setState(errorGameState);
        warStartViewModel.firePropertyChanged();

    }

    /**
     * Creates a list of images based on the provided list of cards.
     *
     * @param imageLinks The list of cards to generate images for.
     * @return A list of Image objects representing the card images.
     * @throws RuntimeException if an error occurs during image processing.
     */
    private java.util.List<Image> makeImages(java.util.List<Card> imageLinks) {
        List<Image> images = new ArrayList<>();
        for (Card card : imageLinks) {
            images.add(imageFactory.create(card));
        }
        return images;
    }
}
