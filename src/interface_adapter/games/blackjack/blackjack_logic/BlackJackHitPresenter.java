package interface_adapter.games.blackjack.blackjack_logic;

import constants.Constants;
import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.games.blackjack.blackjack_start.BlackJackStartState;
import interface_adapter.games.blackjack.blackjack_start.BlackJackStartViewModel;
import use_case.games.blackjack.blackjack_logic.BlackJackHitOutputBoundary;
import use_case.games.blackjack.blackjack_logic.BlackJackOutputGameData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackJackHitPresenter implements BlackJackHitOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackIngameViewModel blackJackIngameViewModel;
    private final ViewManagerModel viewManagerModel;

    private final ImageFactory imageFactory = new CardImageFactory();

    public BlackJackHitPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                 ViewManagerModel viewManagerModel,
                                 BlackJackIngameViewModel blackJackIngameViewModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackJackIngameViewModel = blackJackIngameViewModel;
    }

    @Override
    public void prepareContinueView(BlackJackOutputGameData outputGameData) {
        BlackJackGameState currentBlackJackGameState = blackJackIngameViewModel.getState();

        currentBlackJackGameState.setPlayerImages(makeImages(outputGameData.getGame().getPlayer().getHand()));
        List<Image> dealerImages = new ArrayList<>();

        dealerImages.add(imageFactory.create(outputGameData.getGame().getDealer().getHand().get(0)));
        dealerImages.add(imageFactory.create(Constants.backImage));

        currentBlackJackGameState.setDealerImages(dealerImages);
        currentBlackJackGameState.setGame(outputGameData.getGame());

        this.blackJackIngameViewModel.setState(currentBlackJackGameState);
        this.blackJackIngameViewModel.firePropertyChanged();
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        BlackJackStartState newGameState = blackJackStartViewModel.getState();
        BlackJackGameState endingBlackJackGameState = blackJackIngameViewModel.getState();

        endingBlackJackGameState.setDealerImages(makeImages(outputGameData.getGame().getDealer().getHand()));
        endingBlackJackGameState.setPlayerImages(makeImages(outputGameData.getGame().getPlayer().getHand()));

        endingBlackJackGameState.setGameMessage("You bust! You lose " + -outputGameData.getChange());
        endingBlackJackGameState.setGameEnd(true);

        this.blackJackIngameViewModel.setState(endingBlackJackGameState);
        this.blackJackIngameViewModel.firePropertyChanged();

        newGameState.setBet(0);
        newGameState.setFunds(newGameState.getFunds() + outputGameData.getChange());
        newGameState.setBetError(null);

        this.blackJackStartViewModel.setState(newGameState);
        this.blackJackStartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(blackJackStartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
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
