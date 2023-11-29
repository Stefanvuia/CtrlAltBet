package interface_adapter.war.war_start;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.war.WarGameState;
import interface_adapter.war.war_logic.WarIngameViewModel;
import interface_adapter.war.war_occur.WarOccurViewModel;
import use_case.games.war.war_started.WarStartOutputBoundary;
import use_case.games.war.war_started.WarStartOutputData;
import view.war.WarIngameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WarStartPresenter implements WarStartOutputBoundary {
    private final WarStartViewModel warStartViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WarIngameViewModel warIngameViewModel;
    public WarStartPresenter(WarStartViewModel warStartViewModel, ViewManagerModel viewManagerModel, WarIngameViewModel warIngameViewModel){
        this.warStartViewModel = warStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.warIngameViewModel = warIngameViewModel;
    }
    @Override
    public void prepareWarIngameView(WarStartOutputData outputData) {
        WarGameState ingameStateWar = new WarGameState();
        WarStartState newGameState = warStartViewModel.getState();

        ingameStateWar.setBet(outputData.getBet());
        ingameStateWar.setGame(outputData.getGame());
        ingameStateWar.setPlayerImages(makeImages(outputData.getGame().getPlayer().getHand()));
        ingameStateWar.setDealerImages(makeImages(outputData.getGame().getDealer().getHand()));

        this.warIngameViewModel.setState(ingameStateWar);
        this.viewManagerModel.setActiveView(warIngameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        this.warIngameViewModel.firePropertyChanged();


        if(outputData.getGame().playerWins()){
            newGameState.setFunds(newGameState.getFunds() + newGameState.getBet());
        } else {
            newGameState.setFunds(newGameState.getFunds() - newGameState.getBet());
        }
        newGameState.setBet(0);
        newGameState.setBetError(null);

        this.warStartViewModel.setState(newGameState);
        this.warStartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(warStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareGoToWarView(WarStartOutputData outputData){

    }
    @Override
    public void prepareFailView(String error) {
        WarStartState errorGameState = warStartViewModel.getState();
        errorGameState.setBetError(error);
        warStartViewModel.setState(errorGameState);
        warStartViewModel.firePropertyChanged();

    }
    private java.util.List<Image> makeImages(java.util.List<Card> imageLinks) {
        List<Image> images = new ArrayList<>();
        for (Card card : imageLinks) {
            URL url;
            Image image;
            try {
                url = new URL(card.getImg());
                image = ImageIO.read(url).getScaledInstance(warIngameViewModel.CARD_WIDTH,
                        warIngameViewModel.CARD_HEIGHT,
                        Image.SCALE_SMOOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(image);
        }
        return images;
    }
}