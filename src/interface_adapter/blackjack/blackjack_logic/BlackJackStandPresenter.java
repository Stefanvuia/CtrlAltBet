package interface_adapter.blackjack.blackjack_logic;

import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import use_case.games.blackjack.blackjack_logic.BlackJackOutputGameData;
import use_case.games.blackjack.blackjack_logic.BlackJackStandOutputBoundary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackJackStandPresenter implements BlackJackStandOutputBoundary {
    private final BlackJackStartViewModel blackJackStartViewModel;

    private final BlackJackIngameViewModel blackJackIngameViewModel;
    private final ViewManagerModel viewManagerModel;

    private final ImageFactory imageFactory = new CardImageFactory();

    public BlackJackStandPresenter(BlackJackStartViewModel blackJackStartViewModel,
                                   BlackJackIngameViewModel blackJackIngameViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackIngameViewModel = blackJackIngameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareWinView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Player hand wins! You win " + outputGameData.getChange() + "!",
                outputGameData.getChange(), outputGameData);
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Dealer hand wins! You lose " + -outputGameData.getChange() + "!",
                outputGameData.getChange(), outputGameData);
    }

    @Override
    public void preparePushView(BlackJackOutputGameData outputGameData) {
        gameFinishHelper("Tie!", outputGameData.getChange(), outputGameData);
    }

    private void gameFinishHelper(String message, int change, BlackJackOutputGameData outputGameData) {
        BlackJackStartState newGameState = blackJackStartViewModel.getState();
        BlackJackGameState endingBlackJackGameState = blackJackIngameViewModel.getState();

        endingBlackJackGameState.setGameMessage(message);
        endingBlackJackGameState.setGameEnd(true);
        endingBlackJackGameState.setPlayerImages(makeImages(outputGameData.getGame().getPlayer().getHand()));
        endingBlackJackGameState.setDealerImages(makeImages(outputGameData.getGame().getDealer().getHand()));

        newGameState.setBet(0);
        newGameState.setFunds(newGameState.getFunds() + change);
        newGameState.setBetError(null);

        this.blackJackIngameViewModel.setState(endingBlackJackGameState);
        this.blackJackIngameViewModel.firePropertyChanged();

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
