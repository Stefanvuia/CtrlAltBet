package use_case.games.war.war_logic;


import entity.game_logic.WarGame;
import entity.game_logic.WarGameInterface;

public class WarInputGameData {
    private final WarGameInterface warGameInterface;

    public WarInputGameData(WarGameInterface warGameInterface){
        this.warGameInterface = warGameInterface;
    }

    WarGame getGame(){ return (WarGame) this.warGameInterface; }
}
