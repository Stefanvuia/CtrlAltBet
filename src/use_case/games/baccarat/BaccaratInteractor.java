package use_case.games.baccarat;

import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

import java.util.Map;

/**
 * Interactor class for handling Baccarat game-related operations, including user input processing and game execution.
 */
public class BaccaratInteractor implements BaccaratInputBoundary {

    /**
     * Data access object for managing Baccarat game data.
     */
    final BaccaratDataAccess baccaratDataAccess;

    /**
     * Game logic handler for Baccarat game rules and outcomes.
     */
    final BaccaratGameLogic baccaratGameLogic;

    /**
     * Presenter responsible for displaying Baccarat game output to the user.
     */
    final BaccaratOutputBoundary baccaratPresenter;

    /**
     * Data access interface for handling user history data.
     */
    final HistoryDataAccessInterface historyDAO;

    /**
     * Constructs a BaccaratInteractor object with the specified dependencies.
     *
     * @param cardsAPI          The interface for interacting with the Cards API.
     * @param gameDAO           Data access interface for handling game-related data.
     * @param baccaratPresenter Presenter for displaying Baccarat game output.
     * @param historyDAO        Data access interface for handling user history data.
     */
    public BaccaratInteractor(CardsAPIInterface cardsAPI,
                              GameDataAccessInterface gameDAO,
                              BaccaratOutputBoundary baccaratPresenter,
                              HistoryDataAccessInterface historyDAO) {
        this.baccaratGameLogic = new BaccaratGameLogic(cardsAPI);
        this.baccaratDataAccess = new BaccaratDataAccess(historyDAO, gameDAO);
        this.baccaratPresenter = baccaratPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the Baccarat game based on the provided input data.
     *
     * @param baccaratInputData The input data containing the user's bets and username.
     */
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
