package entity;

import java.util.List;
import java.util.Map;

public interface BaccaratGameInterface {
        String getDeck();

        int sumHand(Player player);

        void addToHand(Player player, Card card);

        void addToHand(Player player, List<Card> cards);

        Map<String, Integer> getBet();
}
