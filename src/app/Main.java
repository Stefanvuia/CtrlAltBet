package app;

import api.CardsAPIObject;
import data_access.UserDataAccessObject;
import entity.account.CommonAccountFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackIngameViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartPresenter;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import use_case.blackjack.BlackJackDataAccessInterface;
import use_case.blackjack.CardsAPIInterface;
import use_case.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartInteractor;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import view.BlackJackStartView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("A");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        BlackJackStartViewModel blackJackStartViewModel= new BlackJackStartViewModel();
        BlackJackIngameViewModel blackJackIngameViewModel = new BlackJackIngameViewModel();

        BlackJackDataAccessInterface blackJackDAO;
        try {
            blackJackDAO = new UserDataAccessObject("./users.csv", new CommonAccountFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CardsAPIInterface blackJackAPI = new CardsAPIObject();

        BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(blackJackStartViewModel,
                blackJackIngameViewModel,
                viewManagerModel);

        BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(blackJackAPI, blackJackDAO, blackJackStartPresenter);
        BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);

        BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, startController);
        views.add(startView, startView.viewName);

        viewManagerModel.setActiveView(startView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
