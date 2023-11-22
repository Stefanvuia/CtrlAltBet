package interface_adapter.blackjack.blackjack_logic;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import use_case.blackjack.blackjack_logic.BlackJackOutputGameData;
import use_case.blackjack.blackjack_logic.BlackJackStandOutputBoundary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BlackJackStandPresenter implements BlackJackStandOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;
    private ViewManagerModel viewManagerModel;

    public BlackJackStandPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   BlackJackStandViewModel blackJackStandViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareWinView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("You win " + outputGameData.getChange() + "!", outputGameData.getChange(), outputGameData);
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Dealer hand wins!", outputGameData.getChange(),outputGameData);
    }

    @Override
    public void preparePushView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Tie!", outputGameData.getChange(), outputGameData);
    }

    private void gameFinishHelper(String message, int change, BlackJackOutputGameData outputGameData) {
        BlackJackStartState newGameState = blackJackStartViewModel.getState();
        BlackJackGameState endingBlackJackGameState = blackJackStandViewModel.getState();

        endingBlackJackGameState.setGameMessage(message);
        endingBlackJackGameState.setGameEnd(true);
        endingBlackJackGameState.setPlayerImages(makeImages(outputGameData.getGame().getPlayer().getHand()));
        endingBlackJackGameState.setDealerImages(makeImages(outputGameData.getGame().getDealer().getHand()));

        newGameState.setBet(0);
        newGameState.setFunds(newGameState.getFunds() + change);
        newGameState.setBetError(null);

        this.blackJackStandViewModel.setState(endingBlackJackGameState);
        this.blackJackStandViewModel.firePropertyChanged();

        this.blackJackStartViewModel.setState(newGameState);
        this.blackJackStartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(blackJackStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    private List<Image> makeImages(List<Card> imageLinks) {
        List<Image> images = new ArrayList<>();
        for (Card card : imageLinks) {
            URL url;
            Image image;
            try {
                url = new URL(card.getImg());
                image = ImageIO.read(url).getScaledInstance(blackJackStandViewModel.CARD_WIDTH,
                        blackJackStandViewModel.CARD_HEIGHT,
                        Image.SCALE_SMOOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(image);
        }
        return images;
    }
}
