package app;

import api.CardsAPIObject;
import data_access.FileUserDataAccessObject;
import entity.user.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.baccarat.BaccaratController;
import interface_adapter.baccarat.BaccaratGameViewModel;
import interface_adapter.baccarat.BaccaratPresenter;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartPresenter;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
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

        BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(blackJackStartViewModel, viewManagerModel, blackJackIngameViewModel);
        BlackJackHitOutputBoundary hitPresenter = new BlackJackHitPresenter(blackJackStartViewModel, viewManagerModel, blackJackIngameViewModel);
        BlackJackStandOutputBoundary standPresenter = new BlackJackStandPresenter(blackJackStartViewModel, blackJackIngameViewModel, viewManagerModel);
        BaccaratOutputBoundary baccaratPresenter = new BaccaratPresenter(baccaratStartViewModel,  baccaratGameViewModel, viewManagerModel);
        ExitOutputBoundary exitPresenter = new ExitPresenter(launchViewModel, viewManagerModel);
        LaunchOutputBoundary launchPresenter = new LaunchPresenter(blackJackStartViewModel, baccaratStartViewModel, viewManagerModel);

        BlackJackHitInputBoundary hitInteractor = new BlackJackHitInteractor(cardsAPI, hitPresenter);
        BlackJackStandInputBoundary standInteractor = new BlackJackStandInteractor(cardsAPI, userDataAccessObject, standPresenter);
        BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(cardsAPI, userDataAccessObject, blackJackStartPresenter);
        ExitInputBoundary exitInteractor = new ExitInteractor(exitPresenter);
        LaunchInputBoundary launchInteractor = new LaunchInteractor(userDataAccessObject, launchPresenter);
        BaccaratInputBoundary baccaratInteractor = new BaccaratInteractor(cardsAPI, userDataAccessObject, baccaratPresenter);

        UserButtonsController userButtonsController = makeUserButtonController(viewManagerModel, loginViewModel, signUpViewModel);
        UserSignupController signupController = makeSignUpController(loginViewModel, signUpViewModel, viewManagerModel, userDataAccessObject);
        UserLoginController loginController = makeUserLoginController(loginViewModel, launchViewModel, viewManagerModel, userDataAccessObject);

        // TODO more helpers if time
        BlackJackHitController hitController = new BlackJackHitController(hitInteractor);
        BlackJackStandController standController = new BlackJackStandController(standInteractor);
        BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);
        ExitController exitController = new ExitController(exitInteractor);
        LaunchController launchController = new LaunchController(launchInteractor);
        BaccaratController baccaratController = new BaccaratController(baccaratInteractor);

        WelcomeView welcomeView = new WelcomeView(welcomeViewModel, userButtonsController);
        SignupView signupView = new SignupView(signupController, signUpViewModel, userButtonsController);
        LoginView loginView = new LoginView(loginController, loginViewModel, userButtonsController);
        BaccaratStartView baccaratStartView = new BaccaratStartView(baccaratStartViewModel, baccaratController, exitController);
        BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, exitController, startController);
        BaccaratGameView baccaratGameView = new BaccaratGameView(baccaratGameViewModel);
        BlackJackIngameView ingameView = new BlackJackIngameView(hitController, standController, exitController, blackJackIngameViewModel);
        MainMenuView mainMenuView = new MainMenuView(launchViewModel, launchController);

        views.add(startView, startView.viewName);
        views.add(baccaratGameView, baccaratGameView.viewName);
        views.add(ingameView, ingameView.viewName);
        views.add(mainMenuView, mainMenuView.viewName);
        views.add(baccaratStartView, baccaratStartView.viewName);
        views.add(welcomeView, welcomeView.viewName);
        views.add(signupView, signupView.viewName);
        views.add(loginView, loginView.viewName);

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

    private static UserButtonsController makeUserButtonController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signUpViewModel) {
        UserButtonsOutputBoundary welcomeButtonsPresenter = new UserButtonsPresenter(viewManagerModel, loginViewModel, signUpViewModel);
        UserButtonsInputBoundary welcomeButtonsInteractor = new UserButtonsInteractor(welcomeButtonsPresenter);
        return new UserButtonsController(welcomeButtonsInteractor);
    }

    private static UserSignupController makeSignUpController(LoginViewModel loginViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel, FileUserDataAccessObject userDataAccessObject) {
        SignupOutputBoundary signupPresenter = new SignupPresenter(loginViewModel, signUpViewModel, viewManagerModel);
        SignupInputBoundary signupInputInteractor = new SignupInteractor(userDataAccessObject, signupPresenter, new CommonUserFactory());
        return new UserSignupController(signupInputInteractor);
    }

    private static UserLoginController makeUserLoginController(LoginViewModel loginViewModel, LaunchViewModel launchViewModel, ViewManagerModel viewManagerModel, FileUserDataAccessObject userDataAccessObject) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(loginViewModel, launchViewModel, viewManagerModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        return new UserLoginController(loginInteractor);
    }
}
