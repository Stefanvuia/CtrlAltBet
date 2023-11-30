package interface_adapter.blackjack.blackjack_start;

import entity.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackIngameViewModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackGameState;
import use_case.games.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.games.blackjack.blackjack_start.BlackJackStartOutputData;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BlackJackStartPresenter implements BlackJackStartOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackIngameViewModel blackJackIngameViewModel;

    private final ViewManagerModel viewManagerModel;

    public BlackJackStartPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   ViewManagerModel viewManagerModel,
                                   BlackJackIngameViewModel blackJackIngameViewModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackJackIngameViewModel = blackJackIngameViewModel;
    }

    @Override
    public void prepareSuccessView(BlackJackStartOutputData outputData) {
        BlackJackGameState ingameStateBlackJack = new BlackJackGameState();

        ingameStateBlackJack.setBet(outputData.getBet());
        ingameStateBlackJack.setGame(outputData.getGame());
        ingameStateBlackJack.setPlayerImages(makeImages(outputData.getGame().getPlayer().getHand()));
        List<Image> dealerImages = new ArrayList<>();

        try {
            dealerImages.add(
                    ImageIO.read(
                            new URL(outputData.getGame().getDealer().getHand().get(0).getImg())).getScaledInstance(
                            blackJackIngameViewModel.CARD_WIDTH,
                            blackJackIngameViewModel.CARD_HEIGHT,
                            Image.SCALE_SMOOTH)
            );
            dealerImages.add(
                    ImageIO.read(
                            new URL(blackJackIngameViewModel.CARD_BACK_URL)).getScaledInstance(
                            blackJackIngameViewModel.CARD_WIDTH,
                            blackJackIngameViewModel.CARD_HEIGHT,
                            Image.SCALE_SMOOTH)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ingameStateBlackJack.setDealerImages(dealerImages);

        this.blackJackIngameViewModel.setState(ingameStateBlackJack);

        this.viewManagerModel.setActiveView(blackJackIngameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        this.blackJackIngameViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        BlackJackStartState errorGameState = blackJackStartViewModel.getState();
        errorGameState.setBetError(error);
        blackJackStartViewModel.setState(errorGameState);
        blackJackStartViewModel.firePropertyChanged();
    }

    private List<Image> makeImages(List<Card> imageLinks) {
        List<Image> images = new ArrayList<>();
        for (Card card : imageLinks) {
            URL url;
            Image image;
            try {
                url = new URL(card.getImg());
                image = ImageIO.read(url).getScaledInstance(blackJackIngameViewModel.CARD_WIDTH,
                        blackJackIngameViewModel.CARD_HEIGHT,
                        Image.SCALE_SMOOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(image);
        }
        return images;
    }
}
