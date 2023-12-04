package use_case.games.baccarat;

import entity.game_logic.BaccaratGame;
import entity.game_logic.BaccaratGameInterface;
import entity.game_logic.BaccaratPlayer;
import entity.game_logic.Player;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

import java.util.Map;
import java.util.Set;

public class BaccaratInteractor implements BaccaratInputBoundary {

    final BaccaratDataAccess baccaratDataAccess;

    final BaccaratGameLogic baccaratGameLogic;

    final BaccaratOutputBoundary baccaratPresenter;

    final HistoryDataAccessInterface historyDAO;

    public BaccaratInteractor(CardsAPIInterface cardsAPI,
                              GameDataAccessInterface gameDAO,
                              BaccaratOutputBoundary baccaratPresenter,
                              HistoryDataAccessInterface historyDAO) {
        this.baccaratGameLogic = new BaccaratGameLogic(cardsAPI);
        this.baccaratDataAccess = new BaccaratDataAccess(historyDAO, gameDAO);
        this.baccaratPresenter = baccaratPresenter;
        this.historyDAO = historyDAO;
    }

    @Override
    public void execute(BaccaratInputData baccaratInputData) {
        String username = baccaratInputData.getUsername();
        Map<String, Integer> bet = baccaratInputData.getBet();

        int sum = 0;
        for (String key : bet.keySet()) {
            sum += bet.get(key);
        }

        if (baccaratDataAccess.checkBet(username, sum)) {
            baccaratGameLogic.startGame();
            String winner = baccaratGameLogic.thirdCardHelper();
            int payout = baccaratDataAccess.payoutHelper(bet, winner, sum);

            BaccaratOutputData outputData = new BaccaratOutputData(
                    winner.toUpperCase() + " win. Your payout is " + payout + "!",
                    baccaratDataAccess.getFund(),
                    baccaratGameLogic.getPlayerHand(),
                    baccaratGameLogic.getBankerHand());
            baccaratPresenter.preparePayoutView(outputData);
        } else {
            BaccaratOutputData outputData = new BaccaratOutputData(
                    "Insufficient funds!",
                    baccaratDataAccess.getFund());
            baccaratPresenter.prepareFailView(outputData);
        }
    }
}
