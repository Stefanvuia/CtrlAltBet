package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackPlayer;
import entity.game_logic.BlackJackGameInterface;
import entity.game_logic.Player;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.GameDataAccessInterface;
import use_case.games.CardsAPIInterface;

/**
 * Interactor class for handling the "stand" action in a Blackjack game.
 */
public class BlackJackStandInteractor implements BlackJackStandInputBoundary {

    /**
     * Interface for interacting with the Cards API.
     */
    final CardsAPIInterface cardsAPI;

    /**
     * Data access interface for handling game-related data.
     */
    final GameDataAccessInterface dataAccessObject;

    /**
     * Presenter responsible for displaying Blackjack stand action output to the user.
     */
    final BlackJackStandOutputBoundary blackJackStandPresenter;

    /**
     * Data access interface for handling user history data.
     */
    final HistoryDataAccessInterface historyDAO;

    /**
     * Constructs a BlackJackStandInteractor object with the specified dependencies.
     *
     * @param cardsAPI                The interface for interacting with the Cards API.
     * @param dataAccessObject        Data access interface for handling game-related data.
     * @param blackJackStandPresenter Presenter for displaying Blackjack stand action output.
     * @param historyDAO              Data access interface for handling user history data.
     */
    public BlackJackStandInteractor(
            CardsAPIInterface cardsAPI,
            GameDataAccessInterface dataAccessObject,
            BlackJackStandOutputBoundary blackJackStandPresenter,
            HistoryDataAccessInterface historyDAO
    ) {
        this.cardsAPI = cardsAPI;
        this.dataAccessObject = dataAccessObject;
        this.blackJackStandPresenter = blackJackStandPresenter;
        this.historyDAO = historyDAO;
    }

    /**
     * Executes the "stand" action in a Blackjack game based on the provided game data.
     *
     * @param blackJackInputGameData The input data containing the Blackjack game state.
     */
    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        BlackJackGameInterface blackJackGameInterface = blackJackInputGameData.getGame();
        Player dealer = blackJackGameInterface.getDealer();
        BlackJackPlayer user = (BlackJackPlayer) blackJackGameInterface.getPlayer();
        String deck = blackJackGameInterface.getDeck();
        Integer playerSum = blackJackGameInterface.sumHand(user);

        int dealerSum = blackJackGameInterface.sumHand(dealer);
        int bet = user.getBet();

        while (dealerSum < 17) {
            blackJackGameInterface.addToHand(dealer, cardsAPI.draw(deck));
            dealerSum = blackJackGameInterface.sumHand(dealer);
        }

        if (blackJackGameInterface.userWin() && Integer.valueOf(user.getHand().size()).equals(2) && playerSum.equals(21)) {
            // Player blackjack
            dataAccessObject.editFund(user.getUsername(), (int) (bet * 2.5));
            historyDAO.addPayout(user.getUsername(), "blackjack", (int) (bet * 1.5));
            blackJackStandPresenter.prepareWinView(new BlackJackOutputGameData(blackJackGameInterface, true, (int) (user.getBet() * 1.5)));
        } else if (blackJackGameInterface.userWin()) {
            // Player win
            dataAccessObject.editFund(user.getUsername(), (user.getBet() * 2));
            historyDAO.addPayout(user.getUsername(), "blackjack", bet);
            blackJackStandPresenter.prepareWinView(new BlackJackOutputGameData(blackJackGameInterface, true, user.getBet()));
        } else if (playerSum.equals(dealerSum)) {
            // Push
            dataAccessObject.editFund(user.getUsername(), user.getBet());
            historyDAO.addPayout(user.getUsername(), "blackjack", 0);
            blackJackStandPresenter.preparePushView(new BlackJackOutputGameData(blackJackGameInterface, true, 0));
        } else {
            // Dealer win
            historyDAO.addPayout(user.getUsername(), "blackjack", -bet);
            blackJackStandPresenter.prepareLoseView(new BlackJackOutputGameData(blackJackGameInterface, true, -user.getBet()));
        }
    }
}
