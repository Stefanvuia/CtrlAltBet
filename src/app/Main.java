package app;

import api.CardsAPIObject;
import data_access.UserDataAccessObject;
import entity.*;
import interface_adapter.start.TestPresenter;
import interface_adapter.start.TestViewModel;
import use_case.BlackJackDataAccessInterface;
import use_case.BlackJackHitInteractor;
import use_case.BlackJackInputGameData;
import use_case.CardsAPIInterface;
import use_case.start.BlackJackStartInputBoundary;
import use_case.start.BlackJackStartInputData;
import use_case.start.BlackJackStartInteractor;
import use_case.start.BlackJackStartOutputBoundary;

import java.io.IOException;

public class Main {
    static CardsAPIInterface a = new CardsAPIObject();

    public static void main(String[] args) throws IOException {
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
        startInteractor.execute(new BlackJackStartInputData("cakev", 0));

        Game game = ((TestPresenter) testPresenter).getTestViewModel().getState().getGame();
        BlackJackInputGameData gameData = new BlackJackInputGameData(game);
        BlackJackHitInteractor hitInteractor = new BlackJackHitInteractor();
        hitInteractor.execute(gameData);


//        TODO now you can pass game into hit and stand
    }
}
