package use_case.games.war.war_started;

import entity.game_logic.WarGameInterface;
import entity.game_logic.WarPlayer;

public class WarStartOutputData {
    private final WarGameInterface game;

    public WarStartOutputData(WarGameInterface game) {
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
