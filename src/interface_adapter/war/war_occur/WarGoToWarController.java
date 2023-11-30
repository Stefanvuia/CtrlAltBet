package interface_adapter.war.war_occur;

import entity.game_logic.WarGameInterface;
import use_case.games.war.war_logic.WarGoToWarInputBoundary;
import use_case.games.war.war_logic.WarInputGameData;

public class WarGoToWarController {
    final WarGoToWarInputBoundary warGoToWarInteractor;
    public WarGoToWarController(WarGoToWarInputBoundary warGoToWarInteractor){this.warGoToWarInteractor = warGoToWarInteractor;}
    public void execute(WarGameInterface game) {
        warGoToWarInteractor.execute(new WarInputGameData(game));
    }
}
