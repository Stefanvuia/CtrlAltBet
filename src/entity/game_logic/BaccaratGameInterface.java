package entity.game_logic;

import entity.Card;

import java.util.List;

public interface BaccaratGameInterface extends Game{
        String getDeck();

        int sumHand(Player player);

        void addToHand(Player player, Card card);

        void addToHand(Player player, List<Card> cards);
}
