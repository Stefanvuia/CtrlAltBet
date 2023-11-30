package interface_adapter.war.war_occur;

import entity.game_logic.WarGameInterface;
import use_case.games.war.war_logic.WarInputGameData;
import use_case.games.war.war_logic.WarSurrenderInputBoundary;

public class WarSurrenderController {
    final WarSurrenderInputBoundary warSurrenderInteractor;

    public WarSurrenderController(WarSurrenderInputBoundary warSurrenderInteractor) {
        this.warSurrenderInteractor = warSurrenderInteractor;
    }

    public void execute(WarGameInterface game) {
        warSurrenderInteractor.execute(new WarInputGameData(game));
    }
}
