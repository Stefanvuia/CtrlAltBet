package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackPlayer;
import entity.Card;
import entity.game_logic.BlackJackGameInterface;
import entity.game_logic.Player;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;


public class BlackJackHitInteractor implements BlackJackHitInputBoundary{
    final CardsAPIInterface cardsAPI;

    final HistoryDataAccessInterface historyDAO;

    final BlackJackHitOutputBoundary blackJackHitPresenter;

    public BlackJackHitInteractor(CardsAPIInterface cardsAPI, BlackJackHitOutputBoundary blackJackHitPresenter, HistoryDataAccessInterface historyDAO) {
        this.cardsAPI = cardsAPI;
        this.blackJackHitPresenter = blackJackHitPresenter;
        this.historyDAO = historyDAO;
    }

    @Override
    public void execute(BlackJackInputGameData blackJackInputGameData) {
        BlackJackGameInterface blackJackGameInterface = blackJackInputGameData.getGame();
        Card card = cardsAPI.draw(blackJackGameInterface.getDeck());
        blackJackGameInterface.addToHand(blackJackGameInterface.getPlayer(), card);

        Player player = blackJackInputGameData.getGame().getPlayer();
        int change = ((BlackJackPlayer) player).getBet();

        if (blackJackGameInterface.sumHand(blackJackGameInterface.getPlayer()) > 21){
            // If player Busts
            historyDAO.addPayout(((BlackJackPlayer) player).getUsername(), "blackjack", -change);
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(blackJackGameInterface, true, -change);
            blackJackHitPresenter.prepareLoseView(outputGameData);
        }
        else{
            BlackJackOutputGameData outputGameData = new BlackJackOutputGameData(blackJackGameInterface, false, 0);
            blackJackHitPresenter.prepareContinueView(outputGameData);
        }
    }
}
