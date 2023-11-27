package app;

import data_access.FileUserDataAccessObject;
import data_access.HistoryDataAccessObject;
import entity.user.CommonUserFactory;
import entity.user.CommonUserHistory;
import entity.user.UserFactory;

import interface_adapter.*;
// <<<<<<< menu
// import interface_adapter.menu.exit.ExitController;
// import interface_adapter.menu.exit.ExitPresenter;
// import interface_adapter.menu.launch_game.LaunchController;
// import interface_adapter.menu.launch_game.LaunchPresenter;
// import interface_adapter.menu.launch_game.LaunchViewModel;
// import use_case.games.baccarat.BaccaratInputBoundary;
// import use_case.games.baccarat.BaccaratInteractor;
// import use_case.games.baccarat.BaccaratOutputBoundary;
// import use_case.games.GameDataAccessInterface;
// import use_case.games.CardsAPIInterface;
// import use_case.games.blackjack.blackjack_logic.*;
// import use_case.games.blackjack.blackjack_start.BlackJackStartInputBoundary;
// import use_case.games.blackjack.blackjack_start.BlackJackStartInteractor;
// import use_case.games.blackjack.blackjack_start.BlackJackStartOutputBoundary;
// import use_case.menu.MenuDataAccessInterface;
// import use_case.menu.exit.ExitInputBoundary;
// import use_case.menu.exit.ExitInteractor;
// import use_case.menu.exit.ExitOutputBoundary;
// import use_case.menu.launch_game.LaunchInputBoundary;
// import use_case.menu.launch_game.LaunchInteractor;
// import use_case.menu.launch_game.LaunchOutputBoundary;
// import view.MainMenuView;
// import view.baccarat.BaccaratGameView;
// import view.baccarat.BaccaratStartView;
// =======
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.update.UpdatePresenter;
import interface_adapter.update.UserUpdateController;

import use_case.history.HistoryDataAccessInterface;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import users.login.LoginInputBoundary;
import users.login.LoginInteractor;
import users.login.LoginOutputBoundary;
import users.login.LoginUserDataAccessInterface;
import users.signup.SignupInputBoundary;
import users.signup.SignupInteractor;
import users.signup.SignupOutputBoundary;
import users.signup.SignupUserDataAccessInterface;
import users.update.UpdateInputBoundary;
import users.update.UpdateInteractor;
import users.update.UpdateOutputBoundary;
import users.update.UpdateUserDataAccessInterface;
import view.*;

/*import users.SignupUserDataAccessInterface;
import users.SignupInputBoundary;
import users.SignupInteractor;
import users.SignupOutputBoundary;
import view.*;*/

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

        // The data for the views, such as username and password. This
        // will be changed by a presenter object that is reporting the
        // results from the use case. This is an observable, and will
        // be observed by the layout manager.
        UserViewModel userViewModel = new UserViewModel();
        FileUserDataAccessObject userDataAccessObject = getDAO();

        // The data for the betting/payout history of every user
        HistoryViewModel historyViewModel = new HistoryViewModel();
        HistoryDataAccessObject historyDataAccessObject = getHistoryDAO();

// <<<<<<< menu
//         BlackJackStartViewModel blackJackStartViewModel= new BlackJackStartViewModel();
//         BlackJackIngameViewModel blackJackIngameViewModel = new BlackJackIngameViewModel();

//         BaccaratStartViewModel baccaratStartViewModel = new BaccaratStartViewModel();
//         BaccaratGameViewModel baccaratGameViewModel = new BaccaratGameViewModel();

//         GameDataAccessInterface gameDAO;
//         MenuDataAccessInterface launchDAO;
//         try {
//             UserDataAccessObject concreteDAO =  new UserDataAccessObject("./users.csv", new CommonAccountFactory());
//             gameDAO = concreteDAO;
//             launchDAO = concreteDAO;
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }

//         CardsAPIInterface cardsAPI = new CardsAPIObject();

//         BaccaratOutputBoundary baccaratPresenter = new BaccaratPresenter(
//                 baccaratStartViewModel,
//                 baccaratGameViewModel,
//                 viewManagerModel
//         );

//         BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(
//                 blackJackStartViewModel,
//                 viewManagerModel,
//                 blackJackIngameViewModel);

//         BlackJackHitOutputBoundary hitPresenter = new BlackJackHitPresenter(
//                 blackJackStartViewModel,
//                 viewManagerModel,
//                 blackJackIngameViewModel);
//         BlackJackHitInputBoundary hitInteractor = new BlackJackHitInteractor(cardsAPI, hitPresenter);

//         BlackJackStandOutputBoundary standPresenter = new BlackJackStandPresenter(
//                 blackJackStartViewModel,
//                 blackJackIngameViewModel,
//                 viewManagerModel
//         );
//         BlackJackStandInputBoundary standInteractor = new BlackJackStandInteractor(cardsAPI, gameDAO, standPresenter);

//         LaunchViewModel launchViewModel = new LaunchViewModel();
//         LaunchOutputBoundary launchPresenter = new LaunchPresenter(blackJackStartViewModel, baccaratStartViewModel, viewManagerModel);

//         LaunchInputBoundary launchInteractor = new LaunchInteractor(launchDAO, launchPresenter);
//         LaunchController launchController = new LaunchController(launchInteractor);

//         ExitOutputBoundary exitPresenter = new ExitPresenter(launchViewModel, viewManagerModel);
//         ExitInputBoundary exitInteractor = new ExitInteractor(exitPresenter);
//         ExitController exitController = new ExitController(exitInteractor);
// =======


        //  The observer watching for changes in the userViewModel. It will
        //  react to changes in application state by changing which view
        //  is showing. This is an anonymous object because we don't need to
        //  refer to it later.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel, userViewModel);

        // The object that knows how to start a use case.
        UserSignupController userSignupController = createUserSignupUseCase(userDataAccessObject);
        UserLoginController userLoginController = loginUserUseCase(userDataAccessObject);
        UserUpdateController userUpdateController = updateUserUseCase(userDataAccessObject);

        HistoryController historyController = historyUserUseCase(historyDataAccessObject);


        // Build the GUI, plugging in the screens.
        createViewsAndAddToPanel(userViewModel, views, userSignupController, userLoginController, userUpdateController, historyController, historyViewModel);

        // Show the first view.
//        cardLayout.show(views, "welcome");
//        cardLayout.show(views, "sign up");
//        cardLayout.show(views, "log in");
//        cardLayout.show(views, "logged in");

//        application.pack();
//        application.setVisible(true);

//  ====================> Uncomment Below to see Game page
//
//        BlackJackStartViewModel blackJackStartViewModel= new BlackJackStartViewModel();
//        BlackJackStandViewModel blackJackStandViewModel = new BlackJackStandViewModel();
//        BlackJackHitViewModel blackJackHitViewModel = new BlackJackHitViewModel();
//
//        BlackJackDataAccessInterface blackJackDAO;
//        try {
//            blackJackDAO = new UserDataAccessObject("./usersX.csv", new CommonAccountFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        CardsAPIInterface blackJackAPI = new CardsAPIObject();
//
//        BlackJackStartOutputBoundary blackJackStartPresenter = new BlackJackStartPresenter(
//                blackJackStartViewModel,
//                blackJackHitViewModel,
//                viewManagerModel,
//                blackJackStandViewModel);
//
//        BlackJackHitOutputBoundary hitPresenter = new BlackJackHitPresenter(
//                blackJackHitViewModel,
//                blackJackStartViewModel,
//                viewManagerModel,
//                blackJackStandViewModel);
//        BlackJackHitInputBoundary hitInteractor = new BlackJackHitInteractor(blackJackAPI, hitPresenter);
//
//        BlackJackStandOutputBoundary standPresenter = new BlackJackStandPresenter(
//                blackJackStartViewModel,
//                blackJackStandViewModel,
//                viewManagerModel
//        );
//        BlackJackStandInputBoundary standInteractor = new BlackJackStandInteractor(blackJackAPI, blackJackDAO, standPresenter);
//
//        BlackJackHitController hitController = new BlackJackHitController(hitInteractor);
//        BlackJackStandController standController = new BlackJackStandController(standInteractor);
//
//        BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(blackJackAPI, blackJackDAO, blackJackStartPresenter);
//        BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);
//
//        BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, startController);
//        views.add(startView, startView.viewName);
//
//        BlackJackIngameView ingameView = new BlackJackIngameView(hitController, standController, blackJackHitViewModel, blackJackStandViewModel);
//        views.add(ingameView, ingameView.viewName);
//
//        viewManagerModel.setActiveView(startView.viewName);
//        viewManagerModel.firePropertyChanged();
// =============================================>  Uncomment above to see the game page
        application.pack();
        application.setSize(1280, 720);
        application.setVisible(true);
    }

    private static void createViewsAndAddToPanel(UserViewModel userViewModel, JPanel views, UserSignupController userSignupController, UserLoginController userLoginController, UserUpdateController userUpdateController, HistoryController historyController, HistoryViewModel historyViewModel) {
        WelcomeView welcomeView = new WelcomeView(userViewModel);
        views.add(welcomeView, ViewManager.WELCOME);

        SignupView signupView = new SignupView(userSignupController, userViewModel);
        views.add(signupView, ViewManager.SIGN_UP);

        LoginView loginView = new LoginView(userLoginController, userViewModel);
        views.add(loginView, ViewManager.LOG_IN);

        LoggedInView loggedInView = new LoggedInView(userViewModel);
        views.add(loggedInView, ViewManager.LOGGED_IN);

        AccountInfoView accountInfoView = new AccountInfoView(userViewModel);
        views.add(accountInfoView, ViewManager.ACCOUNT_INFO);

// <<<<<<< menu
//         BlackJackStartInputBoundary blackJackStartInteractor= new BlackJackStartInteractor(cardsAPI, gameDAO, blackJackStartPresenter);
//         BlackJackStartController startController = new BlackJackStartController(blackJackStartInteractor);

//         BaccaratInputBoundary baccaratInteractor = new BaccaratInteractor(cardsAPI, gameDAO, baccaratPresenter);
//         BaccaratController baccaratController = new BaccaratController(baccaratInteractor);

//         BaccaratStartView baccaratStartView = new BaccaratStartView(baccaratStartViewModel, baccaratController, exitController);
//         views.add(baccaratStartView, baccaratStartView.viewName);

//         BlackJackStartView startView = new BlackJackStartView(blackJackStartViewModel, exitController, startController);
//         views.add(startView, startView.viewName);

//         BaccaratGameView baccaratGameView = new BaccaratGameView(baccaratGameViewModel);
//         views.add(baccaratGameView, baccaratGameView.viewName);

//         BlackJackIngameView ingameView = new BlackJackIngameView(hitController, standController, exitController, blackJackIngameViewModel);
//         views.add(ingameView, ingameView.viewName);

//         MainMenuView mainMenuView = new MainMenuView(launchViewModel, launchController);
//         views.add(mainMenuView, mainMenuView.viewName);

//         viewManagerModel.setActiveView(mainMenuView.viewName);
//         viewManagerModel.firePropertyChanged();
// =======
        BalanceInfoView balanceInfoView = new BalanceInfoView(userUpdateController, userViewModel);
        views.add(balanceInfoView, ViewManager.BALANCE_INFO);

        HistoryView historyView = new HistoryView(userViewModel, historyViewModel, historyController);
        views.add(historyView, ViewManager.BET_HISTORY);
    }

    private static UserSignupController createUserSignupUseCase(SignupUserDataAccessInterface signupDao) {
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter();
        UserFactory userFactory = new CommonUserFactory();
        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                signupDao, signupOutputBoundary, userFactory);
        return new UserSignupController(userSignupInteractor);
    }
    private static UserLoginController loginUserUseCase(LoginUserDataAccessInterface loginDao) {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter();
        LoginInputBoundary userLoginInteractor = new LoginInteractor(loginDao, loginOutputBoundary);
        return new UserLoginController(userLoginInteractor);
    }

    private static UserUpdateController updateUserUseCase(UpdateUserDataAccessInterface UpdateDao) {
        UpdateOutputBoundary updateOutputBoundary = new UpdatePresenter();
        UpdateInputBoundary userUpdateInteractor = new UpdateInteractor(UpdateDao, updateOutputBoundary);
        return new UserUpdateController(userUpdateInteractor);
    }

    // TODO finish implementing, not sure what this does
    private static HistoryController historyUserUseCase(HistoryDataAccessInterface historyDAO) {
        HistoryOutputBoundary historyPresenter = new HistoryPresenter(new HistoryViewModel());
        HistoryInputBoundary historyInteractor = new HistoryInteractor(historyDAO, historyPresenter);
        return new HistoryController(historyInteractor);
    }

    // TODO see if correct
    public static HistoryDataAccessObject getHistoryDAO() {
        HistoryDataAccessObject historyDataAccessObject;
        try {
            historyDataAccessObject = new HistoryDataAccessObject("./history.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return historyDataAccessObject;
    }

    public static FileUserDataAccessObject getDAO() {
        FileUserDataAccessObject fileUserDataAccessObject;
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return fileUserDataAccessObject;
    }

}
