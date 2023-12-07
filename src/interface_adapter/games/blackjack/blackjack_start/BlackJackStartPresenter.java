package interface_adapter.games.blackjack.blackjack_start;

import constants.Constants;
import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.games.blackjack.blackjack_logic.BlackJackGameState;
import interface_adapter.games.blackjack.blackjack_logic.BlackJackIngameViewModel;
import use_case.games.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.games.blackjack.blackjack_start.BlackJackStartOutputData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackJackStartPresenter implements BlackJackStartOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackIngameViewModel blackJackIngameViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ImageFactory imageFactory = new CardImageFactory();

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
        dealerImages.add(imageFactory.create(outputData.getGame().getDealer().getHand().get(0)));
        dealerImages.add(imageFactory.create(Constants.backImage));
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
        List<Image> cardImages = new ArrayList<>();
        for (Card card : imageLinks) {
            Image image;
            image = imageFactory.create(card);
            cardImages.add(image);
        }
        return cardImages;
    }
}
