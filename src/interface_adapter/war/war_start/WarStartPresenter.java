package interface_adapter.war.war_start;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.war.war_occur.WarGameState;
import interface_adapter.war.war_occur.WarIngameViewModel;
import use_case.games.war.war_started.WarStartOutputBoundary;
import use_case.games.war.war_started.WarStartOutputData;

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
    public void prepareSuccessView(WarStartOutputData outputData) {
        WarGameState ingameStateWar = new WarGameState();

        ingameStateWar.setBet(outputData.getBet());
        ingameStateWar.setGame(outputData.getGame());
        ingameStateWar.setPlayerImages(makeImages(outputData.getGame().getPlayer().getHand()));
        List<Image> dealerImages = new ArrayList<>();

        try {
            dealerImages.add(
                    ImageIO.read(
                            new URL(outputData.getGame().getDealer().getHand().get(0).getImg())).getScaledInstance(
                            warIngameViewModel.CARD_WIDTH,
                            warIngameViewModel.CARD_HEIGHT,
                            Image.SCALE_SMOOTH)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ingameStateWar.setDealerImages(dealerImages);

        this.warIngameViewModel.setState(ingameStateWar);

        this.viewManagerModel.setActiveView(warIngameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        this.warIngameViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

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
