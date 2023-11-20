package app;

import api.CardsAPIObject;
import data_access.UserDataAccessObject;
import entity.account.CommonAccountFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartPresenter;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import use_case.blackjack.BlackJackDataAccessInterface;
import use_case.blackjack.CardsAPIInterface;
import use_case.blackjack.blackjack_logic.*;
import use_case.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartInteractor;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import view.blackjack.BlackJackIngameView;
import view.blackjack.BlackJackStartView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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
        BlackJackStandViewModel blackJackStandViewModel = new BlackJackStandViewModel();
        BlackJackHitViewModel blackJackHitViewModel = new BlackJackHitViewModel();

        BlackJackDataAccessInterface blackJackDAO;
        try {
            blackJackDAO = new UserDataAccessObject("./users.csv", new CommonAccountFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CardsAPIInterface blackJackAPI = new CardsAPIObject();

        BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(
                blackJackStartViewModel,
                blackJackHitViewModel,
                viewManagerModel,
                blackJackStandViewModel);

        BlackJackHitOutputBoundary hitPresenter = new BlackJackHitPresenter(
                blackJackHitViewModel,
                blackJackStartViewModel,
                viewManagerModel,
                blackJackStandViewModel);
        BlackJackHitInputBoundary hitInteractor = new BlackJackHitInteractor(blackJackAPI, hitPresenter);

        BlackJackStandOutputBoundary standPresenter = new BlackJackStandPresenter(
                blackJackStartViewModel,
                blackJackStandViewModel,
                viewManagerModel
        );
        BlackJackStandInputBoundary standInteractor = new BlackJackStandInteractor(blackJackAPI, blackJackDAO, standPresenter);

        BlackJackHitController hitController = new BlackJackHitController(hitInteractor);
        BlackJackStandController standController = new BlackJackStandController(standInteractor);

        BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(blackJackAPI, blackJackDAO, blackJackStartPresenter);
        BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);

        BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, startController);
        views.add(startView, startView.viewName);

        BlackJackIngameView ingameView = new BlackJackIngameView(hitController, standController, blackJackHitViewModel, blackJackStandViewModel);
        views.add(ingameView, ingameView.viewName);

        viewManagerModel.setActiveView(startView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(1280, 720);
        application.setVisible(true);
    }
}
