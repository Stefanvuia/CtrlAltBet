package app;

import api.CardsAPIObject;
import data_access.FileUserDataAccessObject;
import data_access.HistoryDataAccessObject;
import entity.user.CommonUserFactory;

import interface_adapter.account_menu.AccountInfoViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.account_menu.history.HistoryController;
import interface_adapter.account_menu.history.HistoryPresenter;

import interface_adapter.account_menu.history.HistoryViewModel;
import interface_adapter.account_menu.reset_graph.ResetController;
import interface_adapter.account_menu.sign_out.SignOutController;
import interface_adapter.account_menu.sign_out.SignOutPresenter;
import interface_adapter.account_menu.update.UpdatePresenter;
import interface_adapter.account_menu.update.UserUpdateController;
import interface_adapter.baccarat.BaccaratController;
import interface_adapter.baccarat.BaccaratGameViewModel;
import interface_adapter.baccarat.BaccaratPresenter;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartPresenter;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.game_menu.account.AccountController;
import interface_adapter.game_menu.account.AccountPresenter;
import interface_adapter.game_menu.exit.ExitController;
import interface_adapter.game_menu.exit.ExitPresenter;
import interface_adapter.game_menu.launch_game.LaunchController;
import interface_adapter.game_menu.launch_game.LaunchPresenter;
import interface_adapter.game_menu.launch_game.LaunchViewModel;
import interface_adapter.launch_menu.WelcomeViewModel;
import interface_adapter.launch_menu.buttons.UserButtonsController;
import interface_adapter.launch_menu.buttons.UserButtonsPresenter;
import interface_adapter.launch_menu.login.LoginPresenter;
import interface_adapter.launch_menu.login.LoginViewModel;
import interface_adapter.launch_menu.login.UserLoginController;
import interface_adapter.launch_menu.sign_up.SignUpViewModel;
import interface_adapter.launch_menu.sign_up.SignupPresenter;
import interface_adapter.launch_menu.sign_up.UserSignupController;

import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.account_menu.history.HistoryInputBoundary;
import use_case.account_menu.history.HistoryInteractor;
import use_case.account_menu.history.HistoryOutputBoundary;

import interface_adapter.war.war_logic.WarIngameViewModel;
import interface_adapter.war.war_occur.*;
import interface_adapter.war.war_start.WarStartController;
import interface_adapter.war.war_start.WarStartPresenter;
import interface_adapter.war.war_start.WarStartViewModel;

import use_case.account_menu.reset_graph.ResetDataAccessInterface;
import use_case.account_menu.reset_graph.ResetInputBoundary;
import use_case.account_menu.reset_graph.ResetInteractor;
import use_case.account_menu.sign_out.SignOutInputBoundary;
import use_case.account_menu.sign_out.SignOutInteractor;
import use_case.account_menu.sign_out.SignOutOutputBoundary;
import use_case.account_menu.update.UpdateInputBoundary;
import use_case.account_menu.update.UpdateInteractor;
import use_case.account_menu.update.UpdateOutputBoundary;
import use_case.game_menu.account.AccountInputBoundary;
import use_case.game_menu.account.AccountInteractor;
import use_case.game_menu.account.AccountOutputBoundary;
import use_case.game_menu.exit.ExitOutputBoundary;
import use_case.games.CardsAPIInterface;
import use_case.games.baccarat.BaccaratInputBoundary;
import use_case.games.baccarat.BaccaratInteractor;
import use_case.games.baccarat.BaccaratOutputBoundary;
import use_case.games.blackjack.blackjack_logic.*;
import use_case.games.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.games.blackjack.blackjack_start.BlackJackStartInteractor;
import use_case.games.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import use_case.game_menu.exit.ExitInputBoundary;
import use_case.game_menu.exit.ExitInteractor;
import use_case.game_menu.launch_game.LaunchInputBoundary;
import use_case.game_menu.launch_game.LaunchInteractor;
import use_case.game_menu.launch_game.LaunchOutputBoundary;

import use_case.games.war.war_logic.*;
import use_case.games.war.war_started.WarStartInputBoundary;
import use_case.games.war.war_started.WarStartInteractor;
import use_case.games.war.war_started.WarStartOutputBoundary;

import use_case.launch_menu.buttons.UserButtonsInputBoundary;
import use_case.launch_menu.buttons.UserButtonsInteractor;
import use_case.launch_menu.buttons.UserButtonsOutputBoundary;
import use_case.launch_menu.login.LoginInputBoundary;
import use_case.launch_menu.login.LoginInteractor;
import use_case.launch_menu.login.LoginOutputBoundary;
import use_case.launch_menu.signup.SignupInputBoundary;
import use_case.launch_menu.signup.SignupInteractor;
import use_case.launch_menu.signup.SignupOutputBoundary;
import view.*;
import view.baccarat.BaccaratGameView;
import view.baccarat.BaccaratStartView;
import view.blackjack.BlackJackIngameView;
import view.blackjack.BlackJackStartView;
import view.launch_menu.LoginView;
import view.launch_menu.SignupView;
import view.launch_menu.WelcomeView;

import view.war.WarIngameView;
import view.war.WarOccurView;
import view.war.WarStartView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.
        JFrame application = new JFrame("CtrlAltBet");

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        FileUserDataAccessObject userDataAccessObject = getDAO();

        HistoryDataAccessObject historyDAO = getHistoryDAO();
        CardsAPIInterface cardsAPI = new CardsAPIObject();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);

        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LaunchViewModel launchViewModel = new LaunchViewModel();

        BlackJackStartViewModel blackJackStartViewModel= new BlackJackStartViewModel();
        BlackJackIngameViewModel blackJackIngameViewModel = new BlackJackIngameViewModel();
        BaccaratStartViewModel baccaratStartViewModel = new BaccaratStartViewModel();
        BaccaratGameViewModel baccaratGameViewModel = new BaccaratGameViewModel();
        WarStartViewModel warStartViewModel = new WarStartViewModel();
        WarIngameViewModel warIngameViewModel = new WarIngameViewModel();
        WarOccurViewModel warOccurViewModel = new WarOccurViewModel();

        AccountInfoViewModel accountInfoViewModel = new AccountInfoViewModel();
        HistoryViewModel historyViewModel = new HistoryViewModel();


        BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(blackJackStartViewModel, viewManagerModel, blackJackIngameViewModel);
        BlackJackHitOutputBoundary hitPresenter = new BlackJackHitPresenter(blackJackStartViewModel, viewManagerModel, blackJackIngameViewModel);
        BlackJackStandOutputBoundary standPresenter = new BlackJackStandPresenter(blackJackStartViewModel, blackJackIngameViewModel, viewManagerModel);

        BaccaratOutputBoundary baccaratPresenter = new BaccaratPresenter(baccaratStartViewModel,  baccaratGameViewModel, viewManagerModel);

        WarStartOutputBoundary warstartPresenter = new WarStartPresenter(warStartViewModel, viewManagerModel, warIngameViewModel, warOccurViewModel);
        WarGoToWarOutputBoundary warGoToWarPresenter = new WarGoToWarPresenter(warStartViewModel, viewManagerModel, warOccurViewModel);
        WarSurrenderOutputBoundary warSurrenderPresenter = new WarSurrenderPresenter(warStartViewModel, viewManagerModel, warOccurViewModel);

        ExitOutputBoundary exitPresenter = new ExitPresenter(launchViewModel, viewManagerModel);
        LaunchOutputBoundary launchPresenter = new LaunchPresenter(blackJackStartViewModel, baccaratStartViewModel, warStartViewModel, viewManagerModel);
        AccountOutputBoundary accountPresenter = new AccountPresenter(accountInfoViewModel, viewManagerModel);
        SignOutOutputBoundary signOutPresenter = new SignOutPresenter(welcomeViewModel, viewManagerModel);
        UpdateOutputBoundary updatePresenter = new UpdatePresenter(accountInfoViewModel);
        HistoryOutputBoundary historyPresenter = new HistoryPresenter(historyViewModel);
      

        BlackJackHitInputBoundary hitInteractor = new BlackJackHitInteractor(cardsAPI, hitPresenter, historyDAO);
        BlackJackStandInputBoundary standInteractor = new BlackJackStandInteractor(cardsAPI, userDataAccessObject, standPresenter, historyDAO);
        BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(cardsAPI, userDataAccessObject, blackJackStartPresenter);
        BaccaratInputBoundary baccaratInteractor = new BaccaratInteractor(cardsAPI, userDataAccessObject, baccaratPresenter, historyDAO);
        WarStartInputBoundary warStartInputInteractor = new WarStartInteractor(cardsAPI, userDataAccessObject, historyDAO, warstartPresenter);
        WarGoToWarInputBoundary warGoToWarInteractor = new WarGoToWarInteractor(cardsAPI, userDataAccessObject, historyDAO, warGoToWarPresenter);
        WarSurrenderInputBoundary warSurrenederInteractor = new WarSurrenderInteractor(cardsAPI, userDataAccessObject, historyDAO, warSurrenderPresenter);

        ExitInputBoundary exitInteractor = new ExitInteractor(exitPresenter);
        LaunchInputBoundary launchInteractor = new LaunchInteractor(userDataAccessObject, launchPresenter);
        AccountInputBoundary accountInteractor = new AccountInteractor(accountPresenter, userDataAccessObject);
        SignOutInputBoundary signoutInteractor = new SignOutInteractor(signOutPresenter);
        UpdateInputBoundary updateInteractor = new UpdateInteractor(userDataAccessObject, updatePresenter);
        HistoryInputBoundary historyInteractor = new HistoryInteractor(historyDAO, historyPresenter);


        UserButtonsController userButtonsController = makeUserButtonController(viewManagerModel, loginViewModel, signUpViewModel);
        UserSignupController signupController = makeSignUpController(loginViewModel, signUpViewModel, viewManagerModel, userDataAccessObject, historyDAO);
        UserLoginController loginController = makeUserLoginController(loginViewModel, launchViewModel, viewManagerModel, userDataAccessObject);
        ResetController resetController = makeResetController(historyDAO);

        // TODO more helpers if time
        BlackJackHitController hitController = new BlackJackHitController(hitInteractor);
        BlackJackStandController standController = new BlackJackStandController(standInteractor);
        BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);

        WarStartController warStartController = new WarStartController(warStartInputInteractor);
        WarGoToWarController warGoToWarController = new WarGoToWarController(warGoToWarInteractor);
        WarSurrenderController warSurrenderController = new WarSurrenderController(warSurrenederInteractor);

        ExitController exitController = new ExitController(exitInteractor);
        LaunchController launchController = new LaunchController(launchInteractor);
        BaccaratController baccaratController = new BaccaratController(baccaratInteractor);
        AccountController accountController = new AccountController(accountInteractor);
        SignOutController signOutController = new SignOutController(signoutInteractor);
        UserUpdateController updateController = new UserUpdateController(updateInteractor);

        HistoryController historyController = new HistoryController(historyInteractor);

        WelcomeView welcomeView = new WelcomeView(welcomeViewModel, userButtonsController);
        SignupView signupView = new SignupView(signupController, signUpViewModel, userButtonsController);
        LoginView loginView = new LoginView(loginController, loginViewModel, userButtonsController);
        BaccaratStartView baccaratStartView = new BaccaratStartView(baccaratStartViewModel, baccaratController, exitController);
        BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, exitController, startController);
        BaccaratGameView baccaratGameView = new BaccaratGameView(baccaratGameViewModel);
        BlackJackIngameView ingameView = new BlackJackIngameView(hitController, standController, exitController, blackJackIngameViewModel);

        AccountInfoView accountInfoView = new AccountInfoView(
                accountInfoViewModel,
                exitController,
                updateController,
                signOutController,
                historyController,
                historyViewModel,
                resetController);
        WarStartView warStartView = new WarStartView(warStartViewModel, exitController, warStartController);
        WarIngameView warIngameView = new WarIngameView(warIngameViewModel);
        WarOccurView warOccurView = new WarOccurView(warOccurViewModel, warGoToWarController, warSurrenderController, exitController);
        
        MainMenuView mainMenuView = new MainMenuView(launchViewModel, launchController, accountController);

        views.add(startView, startView.viewName);
        views.add(baccaratGameView, baccaratGameView.viewName);
        views.add(ingameView, ingameView.viewName);

        views.add(warStartView, warStartView.viewName);
        views.add(warIngameView, warIngameView.viewName);
        views.add(warOccurView, warOccurView.viewName);

        views.add(mainMenuView, mainMenuView.viewName);
        views.add(baccaratStartView, baccaratStartView.viewName);
        views.add(welcomeView, welcomeView.viewName);
        views.add(signupView, signupView.viewName);
        views.add(loginView, loginView.viewName);
        views.add(accountInfoView, accountInfoView.viewName);

        viewManagerModel.setActiveView(welcomeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(1280, 720);
        application.setVisible(true);
    }

    public static FileUserDataAccessObject getDAO() {
        FileUserDataAccessObject fileUserDataAccessObject;
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return fileUserDataAccessObject;
    }

    public static HistoryDataAccessObject getHistoryDAO() {
        HistoryDataAccessObject historyDataAccessObject;
        try {
            historyDataAccessObject = new HistoryDataAccessObject("./history.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return historyDataAccessObject;
    }

    private static UserButtonsController makeUserButtonController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signUpViewModel) {
        UserButtonsOutputBoundary welcomeButtonsPresenter = new UserButtonsPresenter(viewManagerModel, loginViewModel, signUpViewModel);
        UserButtonsInputBoundary welcomeButtonsInteractor = new UserButtonsInteractor(welcomeButtonsPresenter);
        return new UserButtonsController(welcomeButtonsInteractor);
    }

    private static UserSignupController makeSignUpController(LoginViewModel loginViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel,
                                                             FileUserDataAccessObject userDataAccessObject, HistoryDataAccessInterface historyDAO) {
        SignupOutputBoundary signupPresenter = new SignupPresenter(loginViewModel, signUpViewModel, viewManagerModel);
        SignupInputBoundary signupInputInteractor = new SignupInteractor(userDataAccessObject, signupPresenter, new CommonUserFactory(), historyDAO);
        return new UserSignupController(signupInputInteractor);
    }

    private static UserLoginController makeUserLoginController(LoginViewModel loginViewModel, LaunchViewModel launchViewModel, ViewManagerModel viewManagerModel, FileUserDataAccessObject userDataAccessObject) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(loginViewModel, launchViewModel, viewManagerModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        return new UserLoginController(loginInteractor);
    }

    private static ResetController makeResetController(ResetDataAccessInterface resetDAO) {
        ResetInputBoundary resetInteractor = new ResetInteractor(resetDAO);
        return new ResetController(resetInteractor);
    }
}
