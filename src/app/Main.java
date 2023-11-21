package app;

import api.CardsAPIObject;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import entity.account.CommonAccountFactory;

import interface_adapter.*;
import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartPresenter;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.update.UpdatePresenter;
import interface_adapter.update.UserUpdateController;
import use_case.blackjack.BlackJackDataAccessInterface;
/*import interface_adapter.UserSignupController;
import interface_adapter.SignupPresenter;
import interface_adapter.UserViewModel;
import interface_adapter.blackjack.blackjack_logic.TestLogicPresenter;
import interface_adapter.blackjack.blackjack_logic.TestLogicViewModel;
import interface_adapter.blackjack.blackjack_start.TestPresenter;
import interface_adapter.blackjack.blackjack_start.TestViewModel;
import use_case.blackjack.blackjack_logic.BlackJackHitInteractor;
import use_case.blackjack.blackjack_logic.BlackJackInputGameData;*/



import use_case.blackjack.CardsAPIInterface;
import use_case.blackjack.blackjack_logic.*;
import use_case.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartInteractor;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;

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
import view.blackjack.BlackJackIngameView;
import view.blackjack.BlackJackStartView;
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
        JFrame application = new JFrame("Casino Game");
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


        // Build the GUI, plugging in the screens.
        createViewsAndAddToPanel(userViewModel, views, userSignupController, userLoginController, userUpdateController);

        // Show the first view.
//        cardLayout.show(views, "welcome");
//        cardLayout.show(views, "sign up");
//        cardLayout.show(views, "log in");
//        cardLayout.show(views, "logged in");

        application.pack();
        application.setVisible(true);


//
//        BlackJackStartViewModel blackJackStartViewModel= new BlackJackStartViewModel();
//        BlackJackStandViewModel blackJackStandViewModel = new BlackJackStandViewModel();
//        BlackJackHitViewModel blackJackHitViewModel = new BlackJackHitViewModel();
//
//        BlackJackDataAccessInterface blackJackDAO;
//        try {
//            blackJackDAO = new UserDataAccessObject("./users.csv", new CommonAccountFactory());
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

        application.pack();
        application.setSize(1280, 720);
        application.setVisible(true);
    }

    private static void createViewsAndAddToPanel(UserViewModel userViewModel, JPanel views, UserSignupController userSignupController, UserLoginController userLoginController, UserUpdateController userUpdateController) {
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

        BalanceInfoView balanceInfoView = new BalanceInfoView(userUpdateController, userViewModel);
        views.add(balanceInfoView, ViewManager.BALANCE_INFO);
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
