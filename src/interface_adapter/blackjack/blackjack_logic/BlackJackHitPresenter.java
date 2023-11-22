package interface_adapter.blackjack.blackjack_logic;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import use_case.blackjack.blackjack_logic.BlackJackHitOutputBoundary;
import use_case.blackjack.blackjack_logic.BlackJackOutputGameData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BlackJackHitPresenter implements BlackJackHitOutputBoundary {
    private final BlackJackHitViewModel blackJackHitViewModel;
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;
    private ViewManagerModel viewManagerModel;

    public BlackJackHitPresenter(BlackJackHitViewModel blackJackHitViewModel,
                                 BlackJackStartViewModel blackJackStartViewModel,
                                 ViewManagerModel viewManagerModel,
                                 BlackJackStandViewModel blackJackStandViewModel) {
        this.blackJackHitViewModel = blackJackHitViewModel;
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
    }

    @Override
    public void prepareContinueView(BlackJackOutputGameData outputGameData) {
        BlackJackGameState currentBlackJackGameState = blackJackHitViewModel.getState();

        currentBlackJackGameState.setPlayerImages(makeImages(outputGameData.getGame().getPlayer().getHand()));
        List<Image> dealerImages = new ArrayList<>();

        try {
            dealerImages.add(
                    ImageIO.read(
                            new URL(outputGameData.getGame().getDealer().getHand().get(0).getImg())).getScaledInstance(
                            blackJackStandViewModel.CARD_WIDTH,
                            blackJackStandViewModel.CARD_HEIGHT,
                            Image.SCALE_SMOOTH)
            );
            dealerImages.add(
                    ImageIO.read(
                            new URL(blackJackStandViewModel.CARD_BACK_URL)).getScaledInstance(
                                    blackJackStandViewModel.CARD_WIDTH,
                                    blackJackStandViewModel.CARD_HEIGHT,
                                    Image.SCALE_SMOOTH)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentBlackJackGameState.setDealerImages(dealerImages);
        currentBlackJackGameState.setGame(outputGameData.getGame());

        this.blackJackHitViewModel.setState(currentBlackJackGameState);
        this.blackJackStandViewModel.setState(currentBlackJackGameState);

        this.blackJackHitViewModel.firePropertyChanged();
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        BlackJackStartState newGameState = blackJackStartViewModel.getState();
        BlackJackGameState endingBlackJackGameState = blackJackHitViewModel.getState();

        endingBlackJackGameState.setDealerImages(makeImages(outputGameData.getGame().getDealer().getHand()));
        endingBlackJackGameState.setPlayerImages(makeImages(outputGameData.getGame().getPlayer().getHand()));

        endingBlackJackGameState.setGameMessage("You bust!");
        endingBlackJackGameState.setGameEnd(true);

        newGameState.setBet(0);
        newGameState.setFunds(newGameState.getFunds() + outputGameData.getChange());
        newGameState.setBetError(null);

        this.blackJackHitViewModel.setState(endingBlackJackGameState);
        this.blackJackHitViewModel.firePropertyChanged();

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
