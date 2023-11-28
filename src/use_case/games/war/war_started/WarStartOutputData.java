package use_case.games.war.war_started;

import entity.game_logic.BlackJackPlayer;
import entity.game_logic.WarPlayer;
import entity.game_logic.WarGameInterface;

public class WarStartOutputData {
    private WarGameInterface game;

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
