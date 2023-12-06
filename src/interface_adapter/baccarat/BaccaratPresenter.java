package interface_adapter.baccarat;

import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import interface_adapter.ViewManagerModel;
import use_case.games.baccarat.BaccaratOutputBoundary;
import use_case.games.baccarat.BaccaratOutputData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class for the Baccarat game responsible for preparing the view based on the output data.
 */
public class BaccaratPresenter implements BaccaratOutputBoundary {

    /**
     * The ViewModel representing the Baccarat start state.
     */
    private final BaccaratStartViewModel baccaratStartViewModel;

    /**
     * The ViewModel representing the current state of the Baccarat game.
     */
    private final BaccaratGameViewModel baccaratGameViewModel;

    /**
     * The ViewManagerModel for managing active views.
     */
    final ViewManagerModel viewManagerModel;

    private final ImageFactory imageFactory = new CardImageFactory();

    /**
     * Constructs a BaccaratPresenter with the provided ViewModels and ViewManagerModel.
     *
     * @param baccaratStartViewModel The ViewModel for the Baccarat start state.
     * @param baccaratGameViewModel  The ViewModel for the current state of the Baccarat game.
     * @param viewManagerModel       The ViewManagerModel for managing active views.
     */
    public BaccaratPresenter(BaccaratStartViewModel baccaratStartViewModel,
                             BaccaratGameViewModel baccaratGameViewModel,
                             ViewManagerModel viewManagerModel) {
        this.baccaratGameViewModel = baccaratGameViewModel;
        this.baccaratStartViewModel = baccaratStartViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view for displaying the payout information after a Baccarat game.
     *
     * @param baccaratOutputData The output data containing the game result and payout information.
     */
    @Override
    public void preparePayoutView(BaccaratOutputData baccaratOutputData) {
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

        viewManagerModel.setActiveView(baccaratStartViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for displaying a fail message after an unsuccessful Baccarat game.
     *
     * @param baccaratOutputData The output data containing the failure message.
     */
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
        for (Card card : Images) {
            Image image;
            image = imageFactory.create(card);
            cardImages.add(image);
        }
        return cardImages;
    }
}
