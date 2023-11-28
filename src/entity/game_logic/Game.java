package entity.game_logic;

import entity.Card;

public interface Game {
    String getDeck();

    void addToHand(Player player, Card card);
}
