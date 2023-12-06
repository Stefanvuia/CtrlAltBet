package use_case.games.baccarat;

import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.GameDataAccessInterface;

import java.util.Map;

/**
 * Data access class for managing Baccarat game-related data.
 */
public class BaccaratDataAccess {

    /**
     * Data access interface for handling user history data.
     */
    private final HistoryDataAccessInterface historyDAO;

    /**
     * Data access interface for handling game-related data.
     */
    private final GameDataAccessInterface gameDAO;

    /**
     * The username associated with the Baccarat game data.
     */
    private String username;

    /**
     * Constructs a BaccaratDataAccess object with the specified history and game data access interfaces.
     *
     * @param historyDAO The history data access interface.
     * @param gameDAO    The game data access interface.
     */
    public BaccaratDataAccess(HistoryDataAccessInterface historyDAO, GameDataAccessInterface gameDAO) {
        this.historyDAO = historyDAO;
        this.gameDAO = gameDAO;
    }

    /**
     * Checks if a user has sufficient funds to place a bet and updates the user's fund accordingly.
     *
     * @param username The username of the user placing the bet, must exist in the gameDAO.
     * @param betSum   The amount of the bet.
     * @return True if the bet is successful, false otherwise.
     */
    public boolean checkBet(String username, int betSum) {
        /*
         * Preconditions:
         * - The specified username must exist in the game data.
         */
        this.username = username;
        if (gameDAO.getFund(username) >= betSum) {
            gameDAO.editFund(username, -betSum);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates and updates the user's fund based on the outcome of the Baccarat game.
     *
     * @param bet     The map containing the bets on different outcomes (tie, banker, player).
     * @param winner  The winner of the Baccarat game.
     * @param betSum  The total amount of bets placed.
     * @return The payout amount for the user.
     */
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

    /**
     * Retrieves the user's current fund.
     *
     * @return The current fund of the user.
     */
    public int getFund() {
        return gameDAO.getFund(username);
    }
}
