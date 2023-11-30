package interface_adapter.war.war_occur;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackIngameViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.war.WarGameState;
import interface_adapter.war.war_start.WarStartState;
import interface_adapter.war.war_start.WarStartViewModel;
import use_case.games.war.war_logic.WarGoToWarOutputBoundary;
import use_case.games.war.war_logic.WarOutputGameData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WarGoToWarPresenter implements WarGoToWarOutputBoundary {
    private final WarStartViewModel warStartViewModel;
    private final WarOccurViewModel warOccurViewModel;
    private final ViewManagerModel viewManagerModel;
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
            URL url;
            Image image;
            try {
                url = new URL(imageLinks.get(0).getImg());
                image = ImageIO.read(url).getScaledInstance(warOccurViewModel.CARD_WIDTH,
                        warOccurViewModel.CARD_HEIGHT,
                        Image.SCALE_SMOOTH);
                images.add(image);
                for (int i = 0; i < 3; i++){
                    images.add(
                            ImageIO.read(
                                    new URL(warOccurViewModel.CARD_BACK_URL)).getScaledInstance(
                                    warOccurViewModel.CARD_WIDTH,
                                    warOccurViewModel.CARD_HEIGHT,
                                    Image.SCALE_SMOOTH)
                    );
                }
                url = new URL(imageLinks.get(1).getImg());
                image = ImageIO.read(url).getScaledInstance(warOccurViewModel.CARD_WIDTH,
                        warOccurViewModel.CARD_HEIGHT,
                        Image.SCALE_SMOOTH);
                images.add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return images;
    }
}
