package interface_adapter.baccarat;

import entity.Card;
import interface_adapter.ViewManagerModel;
import use_case.baccarat.BaccaratOutputBoundary;
import use_case.baccarat.BaccaratOutputData;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BaccaratPresenter implements BaccaratOutputBoundary {
    private final BaccaratStartViewModel baccaratStartViewModel;

    private final BaccaratGameViewModel baccaratGameViewModel;

    final ViewManagerModel viewManagerModel;

    public BaccaratPresenter(BaccaratStartViewModel baccaratStartViewModel,
                             BaccaratGameViewModel baccaratGameViewModel,
                             ViewManagerModel viewManagerModel) {
        this.baccaratGameViewModel = baccaratGameViewModel;
        this.baccaratStartViewModel = baccaratStartViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePayoutView(BaccaratOutputData baccaratOutputData) {

        ActionListener resetView = e -> {
            viewManagerModel.setActiveView(baccaratStartViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        };

        BaccaratGameState currGameState = baccaratGameViewModel.getState();
        BaccaratStartState currStartState = baccaratStartViewModel.getState();

        List<Card> playerHand = baccaratOutputData.getPlayerHand();
        List<Card> bankerHand = baccaratOutputData.getBankerHand();

        currStartState.setFund(baccaratOutputData.getEndFunds());
        currStartState.setErrorMessage("");

        currGameState.setBankerHand(makeImageFromCard(bankerHand));
        currGameState.setPlayerHand(makeImageFromCard(playerHand));
        currGameState.setGameMessage(baccaratOutputData.getMessage());

        baccaratStartViewModel.setState(currStartState);
        baccaratGameViewModel.setState(currGameState);

        viewManagerModel.setActiveView(baccaratGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        baccaratGameViewModel.firePropertyChanged();
        baccaratStartViewModel.firePropertyChanged();

        Timer timer = new Timer(1000, resetView);
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void prepareFailView(BaccaratOutputData baccaratOutputData) {
        BaccaratStartState currState = baccaratStartViewModel.getState();
        currState.setErrorMessage(baccaratOutputData.getMessage());
        currState.setBet("banker", 0);
        currState.setBet("tie", 0);
        currState.setBet("player", 0);
        baccaratStartViewModel.setState(currState);

        baccaratStartViewModel.firePropertyChanged();
    }

    private List<Image> makeImageFromCard(List<Card> Images) {
        List<Image> cardImages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            URL url = null;
            Image image = null;

            if (i < 2) {
                try {
                    url = new URL(Images.get(i).getImg());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (Images.size() > 2) {
                try {
                    url = new URL(Images.get(i).getImg());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (url != null) {
                try {
                    image = ImageIO.read(url).getScaledInstance(baccaratGameViewModel.CARD_WIDTH,
                            baccaratGameViewModel.CARD_HEIGHT,
                            Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(image != null) {
                cardImages.add(image);
            }
        }
        return cardImages;
    }
}
