package interface_adapter.baccarat;

import entity.Card;
import interface_adapter.ViewManagerModel;
import use_case.baccarat.BaccaratOutputBoundary;
import use_case.baccarat.BaccaratOutputData;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

public class BaccaratPresenter implements BaccaratOutputBoundary {
    private final BaccaratViewModel baccaratViewModel;

    final ViewManagerModel viewManagerModel;

    public BaccaratPresenter(BaccaratViewModel baccaratViewModel, ViewManagerModel viewManagerModel) {
        this.baccaratViewModel = baccaratViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePayoutView(BaccaratOutputData baccaratOutputData) {

        ActionListener resetView = e -> {
            viewManagerModel.setActiveView(baccaratViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        };

        BaccaratState currState = baccaratViewModel.getState();

        List<Card> playerHand = baccaratOutputData.getPlayerHand();
        List<Card> bankerHand = baccaratOutputData.getBankerHand();

        currState.setFund(baccaratOutputData.getEndFunds());
        System.out.println(baccaratOutputData.getEndFunds());
        currState.setBankerHand(bankerHand);
        currState.setPlayerHand(playerHand);
        currState.setGameMessage(baccaratOutputData.getMessage());

        baccaratViewModel.setState(currState);

        viewManagerModel.setActiveView(baccaratViewModel.getSecondaryViewName());
        viewManagerModel.firePropertyChanged();
        baccaratViewModel.firePropertyChanged();

        Timer timer = new Timer(1000, resetView);
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void prepareFailView(BaccaratOutputData baccaratOutputData) {
        BaccaratState currState = baccaratViewModel.getState();
        currState.setGameMessage(baccaratOutputData.getMessage());
        currState.setBet("banker", 0);
        currState.setBet("tie", 0);
        currState.setBet("player", 0);
        baccaratViewModel.setState(currState);

        baccaratViewModel.firePropertyChanged();
    }
}
