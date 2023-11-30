package use_case.games.war.war_started;

import entity.game_logic.*;
import use_case.games.GameDataAccessInterface;
import use_case.games.CardsAPIInterface;

public class WarStartInteractor implements WarStartInputBoundary{
    final CardsAPIInterface cardsAPI;
    final GameDataAccessInterface dataAccess;

    final WarStartOutputBoundary warStartPresenter;

    public WarStartInteractor(
            CardsAPIInterface cardsAPI,
            GameDataAccessInterface dataAccess,
            WarStartOutputBoundary warStartPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.warStartPresenter = warStartPresenter;
    }

    public void execute(WarStartInputData warStartData) {
        String username = warStartData.getUsername();
        int bet = warStartData.getBet();

        if (dataAccess.getFund(username) >= bet) {
            dataAccess.editFund(username, -bet);
            String deckId = cardsAPI.shuffleNew(6);
            Player player = new WarPlayer(bet, username);
            Player dealer = new WarDealer();

            WarGameInterface game = new WarGame(player, dealer, deckId);
            /*entity.Card card = cardsAPI.draw(game.getDeck());
            game.addToHand(player, card);
            game.addToHand(dealer, card);*/

            game.addToHand(player, cardsAPI.draw(game.getDeck()));
            game.addToHand(dealer, cardsAPI.draw(game.getDeck()));

           if (!game.goToWar()){
               if(game.playerWins()){
                   dataAccess.editFund(username, 2*bet);
               }
               warStartPresenter.prepareWarIngameView(new WarStartOutputData(game));
           }
           else{
               warStartPresenter.prepareGoToWarView(new WarStartOutputData(game));
           }

        } else {
            warStartPresenter.prepareFailView("insufficient funds");
        }
    }
}
