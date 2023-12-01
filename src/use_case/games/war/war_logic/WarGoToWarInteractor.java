package use_case.games.war.war_logic;

import entity.game_logic.WarGameInterface;
import entity.game_logic.WarPlayer;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

public class WarGoToWarInteractor implements WarGoToWarInputBoundary{
    final CardsAPIInterface cardsAPI;
    final GameDataAccessInterface dataAccess;
    final HistoryDataAccessInterface historyDAO;
    final WarGoToWarOutputBoundary warGoToWarPresenter;

    public WarGoToWarInteractor(CardsAPIInterface cardsAPI,
                                GameDataAccessInterface dataAccess,
                                HistoryDataAccessInterface historyDAO,
                                WarGoToWarOutputBoundary warGoToWarPresenter){
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.warGoToWarPresenter = warGoToWarPresenter;
        this.historyDAO = historyDAO;
    }
    @Override
    public void execute(WarInputGameData warInputGameData) {
        WarGameInterface game = warInputGameData.getGame();
        int bet = warInputGameData.getBet();
        String username = warInputGameData.getUser();

        if(dataAccess.getFund(username) >= bet){
            dataAccess.editFund(username, -bet);

            bet = bet * 2;

            /*entity.Card card = cardsAPI.draw(game.getDeck());
            game.addToHand(game.getPlayer(), card);
            game.addToHand(game.getDealer(), card);*/

            game.addToHand(game.getPlayer(), cardsAPI.draw(game.getDeck()));
            game.addToHand(game.getDealer(), cardsAPI.draw(game.getDeck()));

            int change = 0;
            if(!game.goToWar()){
                if(game.playerWins()){
                    change = bet + bet * 1/4;
                }
            }else {
                change = bet * 2;
            }
            historyDAO.addPayout(username, "war", change - bet);
            dataAccess.editFund(username, change);
            warGoToWarPresenter.preparePayoutView(new WarOutputGameData(game));
        } else{
            warGoToWarPresenter.prepareFailView("insufficient funds to double the wager");
        }

    }

}
