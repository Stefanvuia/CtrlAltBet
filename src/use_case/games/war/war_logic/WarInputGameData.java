package use_case.games.war.war_logic;


import entity.game_logic.WarGame;
import entity.game_logic.WarGameInterface;
import entity.game_logic.WarPlayer;

public class WarInputGameData {
    private final WarGameInterface game;


    public WarInputGameData(WarGameInterface game){

        this.game = game;
    }

    WarGame getGame(){ return (WarGame) this.game; }
    public String getUser() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getUsername();
    }

    public int getBet() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getBet();
    }
}
