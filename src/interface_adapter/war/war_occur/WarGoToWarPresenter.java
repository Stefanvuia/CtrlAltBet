package interface_adapter.war.war_occur;

import constants.Constants;
import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.war.WarGameState;
import interface_adapter.war.war_start.WarStartState;
import interface_adapter.war.war_start.WarStartViewModel;
import use_case.games.war.war_logic.WarGoToWarOutputBoundary;
import use_case.games.war.war_logic.WarOutputGameData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WarGoToWarPresenter implements WarGoToWarOutputBoundary {
    private final WarStartViewModel warStartViewModel;
    private final WarOccurViewModel warOccurViewModel;
    private final ViewManagerModel viewManagerModel;

    private final ImageFactory imageFactory = new CardImageFactory();
    public WarGoToWarPresenter(WarStartViewModel warStartViewModel,
                                 ViewManagerModel viewManagerModel,
                                 WarOccurViewModel warOccurViewModel) {
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.warOccurViewModel = warOccurViewModel;
    }
    @Override
    public void preparePayoutView(WarOutputGameData warOutputGameData) {
        WarGameState currentState = warOccurViewModel.getState();
        WarStartState newGameState = warStartViewModel.getState();
        currentState.setDidGoToWar(true);

        currentState.setDealerImages(makeImages(warOutputGameData.getGame().getDealer().getHand()));
        currentState.setPlayerImages(makeImages(warOutputGameData.getGame().getPlayer().getHand()));

        this.warOccurViewModel.firePropertyChanged();
        if(warOutputGameData.getGame().playerWins()){
            newGameState.setFunds(newGameState.getFunds() + (newGameState.getBet() * 3/2));
        } else if (warOutputGameData.getGame().goToWar()){
            newGameState.setFunds(newGameState.getFunds() + 2 * newGameState.getBet());
        } else{
            newGameState.setFunds(newGameState.getFunds() - 2 * newGameState.getBet());
        }
        newGameState.setBet(0);
        newGameState.setBetError(null);

        this.warStartViewModel.setState(newGameState);
        this.warStartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(warStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        WarGameState errorGameState = warOccurViewModel.getState();
        errorGameState.setErrorMessage(error);
        warOccurViewModel.setState(errorGameState);
        warOccurViewModel.firePropertyChanged();

        errorGameState.setErrorMessage(null);

    }
    private List<Image> makeImages(List<Card> imageLinks) {
        List<Image> images = new ArrayList<>();
        images.add(imageFactory.create(imageLinks.get(0)));
        for (int i = 0; i < 3; i++){
            images.add(imageFactory.create(Constants.backImage));
        }
        images.add(imageFactory.create(imageLinks.get(1)));
        return images;
    }
}
