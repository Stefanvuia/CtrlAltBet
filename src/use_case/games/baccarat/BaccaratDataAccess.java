package use_case.games.baccarat;

import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.GameDataAccessInterface;

import java.util.Map;

public class BaccaratDataAccess {
    private HistoryDataAccessInterface historyDAO;
    private GameDataAccessInterface gameDAO;

    private String username;

    public BaccaratDataAccess(HistoryDataAccessInterface historyDAO, GameDataAccessInterface gameDAO) {
        this.historyDAO = historyDAO;
        this.gameDAO = gameDAO;
    }

    public boolean checkBet(String username, int betSum) {
        this.username = username;
        if (gameDAO.getFund(username) >= betSum) {
            gameDAO.editFund(username, -betSum);
            return true;
        } else {
            return false;
        }
    }

    public int payoutHelper(Map<String, Integer> bet, String winner, int betSum) {
        int payout;
        if (winner.equals("tie")) {
            payout = bet.get(winner) * 8;
        } else if (winner.equals("banker")) {
            payout = (int) (bet.get(winner) * 0.95);
        } else {
            payout = bet.get(winner);
        }
        gameDAO.editFund(username, payout + bet.get(winner));

        payout = payout + bet.get(winner) - betSum;
        historyDAO.addPayout(username, "baccarat", payout);
        return payout;
    }

    public int getFund() {
        return gameDAO.getFund(username);
    }
}
