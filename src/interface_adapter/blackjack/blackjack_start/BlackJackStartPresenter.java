package interface_adapter.blackjack.blackjack_start;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackStandViewModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackGameState;
import interface_adapter.blackjack.blackjack_logic.BlackJackHitViewModel;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartOutputData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BlackJackStartPresenter  implements BlackJackStartOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;
    private final BlackJackHitViewModel blackJackHitViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;

    private final ViewManagerModel viewManagerModel;

    public BlackJackStartPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   BlackJackHitViewModel blackJackHitViewModel,
                                   ViewManagerModel viewManagerModel,
                                   BlackJackStandViewModel blackJackStandViewModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackHitViewModel = blackJackHitViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
    }

    @Override
    public void prepareSuccessView(BlackJackStartOutputData outputData) {
        BlackJackGameState ingameStateBlackJack = blackJackHitViewModel.getState();
        ingameStateBlackJack.setBet(outputData.getBet());
        ingameStateBlackJack.setGame(outputData.getGame());
        ingameStateBlackJack.setGameEnd(false);

        ingameStateBlackJack.setPlayerImages(makeImages(outputData.getGame().getPlayer().getHand()));
        List<Image> dealerImages = new ArrayList<>();

        try {
            dealerImages.add(
                    ImageIO.read(
                            new URL(outputData.getGame().getDealer().getHand().get(0).getImg())).getScaledInstance(
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

        ingameStateBlackJack.setDealerImages(dealerImages);

        this.blackJackHitViewModel.setState(ingameStateBlackJack);
        this.blackJackStandViewModel.setState(ingameStateBlackJack);

        this.viewManagerModel.setActiveView(blackJackHitViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        this.blackJackHitViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        BlackJackStartState errorGameState = blackJackStartViewModel.getState();
        errorGameState.setBetError(error);
        blackJackStartViewModel.setState(errorGameState);
        blackJackStartViewModel.firePropertyChanged();
    }

    private java.util.List<Image> makeImages(java.util.List<Card> imageLinks) {
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
