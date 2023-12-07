package interface_adapter.games.war.war_occur;

import constants.Constants;
import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.games.war.WarGameState;
import interface_adapter.games.war.war_start.WarStartState;
import interface_adapter.games.war.war_start.WarStartViewModel;
import use_case.games.war.war_logic.WarGoToWarOutputBoundary;
import use_case.games.war.war_logic.WarOutputGameData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The WarGoToWarPresenter class implements the WarGoToWarOutputBoundary interface and is responsible
 * for preparing the presentation logic related to the "Go to War" action in the War card game.
 * It collaborates with the WarStartViewModel, WarOccurViewModel, and ViewManagerModel to update the
 * UI and handle various game states and outcomes.
 */
public class WarGoToWarPresenter implements WarGoToWarOutputBoundary {

    /**
     * The ViewModel for the start state of the War card game.
     */
    private final WarStartViewModel warStartViewModel;

    /**
     * The ViewModel for when a war occurs cards equal
     */
    private final WarOccurViewModel warOccurViewModel;

    /**
     * The model managing the active views in the application.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Image factory that creates images for cards
     */
    private final ImageFactory imageFactory = new CardImageFactory();

    /**
     * Constructs a new WarGoToWarPresenter with the specified ViewModels and ViewManagerModel.
     *
     * @param warStartViewModel The ViewModel for the start state of the War card game.
     * @param viewManagerModel  The model managing the active views in the application.
     * @param warOccurViewModel The ViewModel for the occurrence state of the War card game.
     */
    public WarGoToWarPresenter(WarStartViewModel warStartViewModel,
                               ViewManagerModel viewManagerModel,
                               WarOccurViewModel warOccurViewModel) {
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.warOccurViewModel = warOccurViewModel;
    }

    /**
     * Prepares the payout view based on the provided WarOutputGameData, updating the UI components
     * and managing game state accordingly.
     *
     * @param warOutputGameData The data containing the outcome of the "Go to War" action.
     */
    @Override
    public void preparePayoutView(WarOutputGameData warOutputGameData) {
        WarGameState currentState = warOccurViewModel.getState();
        WarStartState newGameState = warStartViewModel.getState();
        currentState.setDidGoToWar(true);

        // Update game state based on war outcome
        currentState.setDealerImages(makeImages(warOutputGameData.getGame().getDealer().getHand()));
        currentState.setPlayerImages(makeImages(warOutputGameData.getGame().getPlayer().getHand()));

        // Notify listeners of the state change
        this.warOccurViewModel.firePropertyChanged();

        // Update funds based on game outcome
        if (warOutputGameData.getGame().playerWins()) {
            newGameState.setFunds(newGameState.getFunds() + (newGameState.getBet() * 3 / 2));
        } else if (warOutputGameData.getGame().goToWar()) {
            newGameState.setFunds(newGameState.getFunds() + 2 * newGameState.getBet());
        } else {
            newGameState.setFunds(newGameState.getFunds() - 2 * newGameState.getBet());
        }

        // Reset bet-related values
        newGameState.setBet(0);
        newGameState.setBetError(null);

        // Update the start state ViewModel and notify listeners
        this.warStartViewModel.setState(newGameState);
        this.warStartViewModel.firePropertyChanged();

        // Set the active view and notify listeners
        this.viewManagerModel.setActiveView(warStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view by updating the UI components with the provided error message.
     *
     * @param error The error message to display in the fail view.
     */
    @Override
    public void prepareFailView(String error) {
        WarGameState errorGameState = warOccurViewModel.getState();

        // Set the error message in the occurrence state ViewModel
        errorGameState.setErrorMessage(error);
        warOccurViewModel.setState(errorGameState);
        warOccurViewModel.firePropertyChanged();

        // Reset the error message
        errorGameState.setErrorMessage(null);

    }

    /**
     * Creates a list of images based on the provided list of cards with 3 card backs in between the two actual cards.
     *
     * @param imageLinks The list of cards to generate images for.
     * @return A list of Image objects representing the card images.
     * @throws RuntimeException if an error occurs during image processing.
     */
    private List<Image> makeImages(List<Card> imageLinks) {
        List<Image> images = new ArrayList<>();

        images.add(imageFactory.create(imageLinks.get(0)));
        for (int i = 0; i < 3; i++) {
            images.add(imageFactory.create(Constants.backImage));
        }
        images.add(imageFactory.create(imageLinks.get(1)));

        return images;
    }
}
