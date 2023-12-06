package entity.game_logic;

import entity.cards.Card;


public interface WarGameInterface extends Game {

    void addToHand(Player player, Card card);

    boolean goToWar();

    int sumHand(Player player);

    boolean playerWins();

    Player getPlayer();

    Player getDealer();

    String getDeck();
}
