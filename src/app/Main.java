package app;

import api.CardsAPIObject;
import data_access.UserDataAccessObject;
import entity.account.CommonAccountFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.baccarat.BaccaratController;
import interface_adapter.baccarat.BaccaratGameViewModel;
import interface_adapter.baccarat.BaccaratPresenter;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartPresenter;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.menu.exit.ExitController;
import interface_adapter.menu.exit.ExitPresenter;
import interface_adapter.menu.launch_game.LaunchController;
import interface_adapter.menu.launch_game.LaunchPresenter;
import interface_adapter.menu.launch_game.LaunchViewModel;
import use_case.games.baccarat.BaccaratInputBoundary;
import use_case.games.baccarat.BaccaratInteractor;
import use_case.games.baccarat.BaccaratOutputBoundary;
import use_case.games.GameDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.blackjack.blackjack_logic.*;
import use_case.games.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.games.blackjack.blackjack_start.BlackJackStartInteractor;
import use_case.games.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.menu.MenuDataAccessInterface;
import use_case.menu.exit.ExitInputBoundary;
import use_case.menu.exit.ExitInteractor;
import use_case.menu.exit.ExitOutputBoundary;
import use_case.menu.launch_game.LaunchInputBoundary;
import use_case.menu.launch_game.LaunchInteractor;
import use_case.menu.launch_game.LaunchOutputBoundary;
import view.MainMenuView;
import view.baccarat.BaccaratGameView;
import view.baccarat.BaccaratStartView;
import view.blackjack.BlackJackIngameView;
import view.blackjack.BlackJackStartView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame application = new JFrame("CtrlAltBet");
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

        BaccaratStartViewModel baccaratStartViewModel = new BaccaratStartViewModel();
        BaccaratGameViewModel baccaratGameViewModel = new BaccaratGameViewModel();

        GameDataAccessInterface gameDAO;
        MenuDataAccessInterface launchDAO;
        try {
            UserDataAccessObject concreteDAO =  new UserDataAccessObject("./users.csv", new CommonAccountFactory());
            gameDAO = concreteDAO;
            launchDAO = concreteDAO;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CardsAPIInterface cardsAPI = new CardsAPIObject();

        BaccaratOutputBoundary baccaratPresenter = new BaccaratPresenter(
                baccaratStartViewModel,
                baccaratGameViewModel,
                viewManagerModel
        );

        BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(
                blackJackStartViewModel,
                viewManagerModel,
                blackJackIngameViewModel);

        BlackJackHitOutputBoundary hitPresenter = new BlackJackHitPresenter(
                blackJackStartViewModel,
                viewManagerModel,
                blackJackIngameViewModel);
        BlackJackHitInputBoundary hitInteractor = new BlackJackHitInteractor(cardsAPI, hitPresenter);

        BlackJackStandOutputBoundary standPresenter = new BlackJackStandPresenter(
                blackJackStartViewModel,
                blackJackIngameViewModel,
                viewManagerModel
        );
        BlackJackStandInputBoundary standInteractor = new BlackJackStandInteractor(cardsAPI, gameDAO, standPresenter);

        LaunchViewModel launchViewModel = new LaunchViewModel();
        LaunchOutputBoundary launchPresenter = new LaunchPresenter(blackJackStartViewModel, baccaratStartViewModel, viewManagerModel);

        LaunchInputBoundary launchInteractor = new LaunchInteractor(launchDAO, launchPresenter);
        LaunchController launchController = new LaunchController(launchInteractor);

        ExitOutputBoundary exitPresenter = new ExitPresenter(launchViewModel, viewManagerModel);
        ExitInputBoundary exitInteractor = new ExitInteractor(exitPresenter);
        ExitController exitController = new ExitController(exitInteractor);

        BlackJackHitController hitController = new BlackJackHitController(hitInteractor);
        BlackJackStandController standController = new BlackJackStandController(standInteractor);

        BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(cardsAPI, gameDAO, blackJackStartPresenter);
        BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);

        BaccaratInputBoundary baccaratInteractor = new BaccaratInteractor(cardsAPI, gameDAO, baccaratPresenter);
        BaccaratController baccaratController = new BaccaratController(baccaratInteractor);

        BaccaratStartView baccaratStartView = new BaccaratStartView(baccaratStartViewModel, baccaratController, exitController);
        views.add(baccaratStartView, baccaratStartView.viewName);

        BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, exitController, startController);
        views.add(startView, startView.viewName);

        BaccaratGameView baccaratGameView = new BaccaratGameView(baccaratGameViewModel);
        views.add(baccaratGameView, baccaratGameView.viewName);

        BlackJackIngameView ingameView = new BlackJackIngameView(hitController, standController, exitController, blackJackIngameViewModel);
        views.add(ingameView, ingameView.viewName);

        MainMenuView mainMenuView = new MainMenuView(launchViewModel, launchController);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(1280, 720);
        application.setVisible(true);
    }
}
