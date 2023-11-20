package entity.game_logic;

import entity.Card;

public interface Game {
    String getDeck();

    int sumHand(Player player);

    void addToHand(Player player, Card card);
}
