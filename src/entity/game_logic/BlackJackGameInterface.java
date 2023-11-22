package entity.game_logic;

import entity.Card;

import java.util.List;

public interface BlackJackGameInterface extends Game{
    int sumHand(Player player);

    void addToHand(Player player, Card card);

    void addToHand(Player player, List<Card> cards);

    boolean userWin();

    Player getPlayer();

    Player getDealer();

    String getDeck();
}
