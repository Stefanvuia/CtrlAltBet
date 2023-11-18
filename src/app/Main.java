package app;

import api.CardsAPIObject;
import data_access.UserDataAccessObject;
import entity.*;
import entity.account.AccountFactory;
import entity.account.CommonAccountFactory;
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

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static CardsAPIInterface a = new CardsAPIObject();

    public static void main(String[] args) throws IOException {
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
