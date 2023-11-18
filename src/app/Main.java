package app;

import api.CardsAPIObject;
import data_access.FileUserDataAccessObject;
import data_access.UserDataAccessObject;
import entity.*;
import entity.account.AccountFactory;
import entity.account.CommonAccountFactory;
import interface_adapter.UserSignupController;
import interface_adapter.UserSignupPresenter;
import interface_adapter.UserViewModel;
import interface_adapter.blackjack.blackjack_logic.TestLogicPresenter;
import interface_adapter.blackjack.blackjack_logic.TestLogicViewModel;
import interface_adapter.blackjack.blackjack_start.TestPresenter;
import interface_adapter.blackjack.blackjack_start.TestViewModel;
import use_case.blackjack.blackjack_logic.BlackJackHitInteractor;
import use_case.blackjack.blackjack_logic.BlackJackInputGameData;
import use_case.blackjack.CardsAPIInterface;
import use_case.blackjack.blackjack_logic.BlackJackStandInteractor;
import use_case.blackjack.blackjack_start.BlackJackStartInputBoundary;
import use_case.blackjack.blackjack_start.BlackJackStartInputData;
import use_case.blackjack.blackjack_start.BlackJackStartInteractor;
import use_case.blackjack.blackjack_start.BlackJackStartOutputBoundary;
import users.UserSignupDataAccessInterface;
import users.UserSignupInputBoundary;
import users.UserSignupInteractor;
import users.UserSignupOutputBoundary;
import view.*;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static CardsAPIInterface a = new CardsAPIObject();

    public static void main(String[] args) throws IOException {

        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.
        JFrame application = new JFrame("Casino");
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // The data for the views, such as username and password. This
        // will be changed by a presenter object that is reporting the
        // results from the use case. This is an observable, and will
        // be observed by the layout manager.
        UserViewModel userViewModel = new UserViewModel();


        /*
         The observer watching for changes in the userViewModel. It will
         react to changes in application state by changing which view
         is showing. This is an anonymous object because we don't need to
         refer to it later.
        */
        new ViewManager(views, cardLayout, userViewModel);

        // The object that knows how to start a use case.
        UserSignupController userSignupController = createUserSignupUseCase();
        // Build the GUI, plugging in the screens.
        createViewsAndAddToPanel(userViewModel, views, userSignupController);

        // Show the first view.
        cardLayout.show(views, "welcome");
//        cardLayout.show(views, "sign up");
//        cardLayout.show(views, "log in");
//        cardLayout.show(views, "logged in");

        application.pack();
        application.setVisible(true);


        //        Scanner scanner = new Scanner(System.in);
//        boolean play = true;
//        while(play) {
//            gameRunner();
//            System.out.println("Play again? (Y/N)");
//            if(scanner.nextLine().equals("N")) {
//                play = false;
//            }
//        }
    }

    private static void createViewsAndAddToPanel(UserViewModel userViewModel, JPanel views, UserSignupController userSignupController) {
        WelcomeView welcomeView = new WelcomeView(userViewModel);
        views.add(welcomeView, ViewManager.WELCOME);

        SignupView signupView = new SignupView(userSignupController, userViewModel);
        views.add(signupView, ViewManager.SIGN_UP);

        LoginView loginView = new LoginView(userViewModel);
        views.add(loginView, ViewManager.LOG_IN);

        LoggedInView loggedInView = new LoggedInView();
        views.add(loggedInView, ViewManager.LOGGED_IN);
    }

    private static UserSignupController createUserSignupUseCase() {
        UserSignupDataAccessInterface user;
        try {
            user = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        UserSignupOutputBoundary userSignupOutputBoundary = new UserSignupPresenter();
        UserFactory userFactory = new CommonUserFactory();
        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                user, userSignupOutputBoundary, userFactory);
        return new UserSignupController(userSignupInteractor);
    }

    public static void gameRunner() {
        AccountFactory accountFactory = new CommonAccountFactory();
        UserDataAccessObject userDataAccessObject;
        try{
            userDataAccessObject = new UserDataAccessObject("./users.csv", accountFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TestViewModel viewModel = new TestViewModel();
        BlackJackStartOutputBoundary testPresenter = new TestPresenter(viewModel);

        BlackJackStartInputBoundary startInteractor = new BlackJackStartInteractor(a, userDataAccessObject, testPresenter);
        startInteractor.execute(new BlackJackStartInputData("cakev", 2));

        Game game = ((TestPresenter) testPresenter).getTestViewModel().getState().getGame();
        BlackJackInputGameData gameData = new BlackJackInputGameData(game);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter H to hit, S to stand");
        String choice = scanner.nextLine();

        TestLogicViewModel viewModel2 = new TestLogicViewModel();
        BlackJackHitInteractor hitInteractor = new BlackJackHitInteractor(a, new TestLogicPresenter(viewModel2));
        BlackJackStandInteractor standInteractor = new BlackJackStandInteractor(a, userDataAccessObject, new TestLogicPresenter(viewModel2));

        if (choice.equals("H")) {
            hitInteractor.execute(gameData);
        } else {
            standInteractor.execute(gameData);
        }

        while (!(viewModel2.getState().getEnd())) {
            System.out.println("Enter H to hit, S to stand");
            if(scanner.nextLine().equals("H")) {
                hitInteractor.execute(new BlackJackInputGameData(viewModel2.getState().getGame()));
            } else {
                standInteractor.execute(new BlackJackInputGameData(viewModel2.getState().getGame()));
            }
        }
    }
}
