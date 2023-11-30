package use_case.games.war.war_logic;

import entity.game_logic.WarGameInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

public class WarSurrenderInteractor implements WarSurrenderInputBoundary{
    final CardsAPIInterface cardsAPI;
    final GameDataAccessInterface dataAccess;
    final WarSurrenderOutputBoundary warSurrenderPresenter;
    public WarSurrenderInteractor(CardsAPIInterface cardsAPI,
                                GameDataAccessInterface dataAccess,
                                WarSurrenderOutputBoundary warSurrenderPresenter) {
        this.cardsAPI = cardsAPI;
        this.dataAccess = dataAccess;
        this.warSurrenderPresenter = warSurrenderPresenter;
    }

    @Override
    public void execute(WarInputGameData warInputGameData) {
        WarGameInterface game = warInputGameData.getGame();
        int bet = warInputGameData.getBet();
        String username = warInputGameData.getUser();

        dataAccess.editFund(username, bet / 2);
        warSurrenderPresenter.prepareSurrenderView(new WarOutputGameData(game));
    }
}
