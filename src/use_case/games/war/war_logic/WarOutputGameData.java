package use_case.games.war.war_logic;

import entity.game_logic.WarGameInterface;
import entity.game_logic.WarPlayer;

public class WarOutputGameData {
    private final WarGameInterface game;

    public WarOutputGameData(WarGameInterface game) {
        this.game = game;
    }

    public WarGameInterface getGame() {
        return this.game;
    }

    public String getUser() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getUsername();
    }

    public int getBet() {
        WarPlayer user = (WarPlayer) game.getPlayer();
        return user.getBet();
    }
}
