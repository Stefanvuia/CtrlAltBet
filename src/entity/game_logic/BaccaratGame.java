package entity.game_logic;

import entity.Card;

import java.util.List;
import java.util.Set;

public class BaccaratGame implements BaccaratGameInterface {
    private static final Set<String> VALUES = Set.of("JACK", "QUEEN", "KING", "10");
    private final Player player;
    private final Player banker;
    private final String deckId;

    public BaccaratGame(Player player, Player banker, String deckId) {
        this.player = player;
        this.banker = banker;
        this.deckId = deckId;
    }

    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int sum = 0;
        for (Card card : hand) {
            if (card.getValue().equals("ACE")) {
                sum++;
            } else if (VALUES.contains(card.getValue())) {
            } else {
                sum += Integer.parseInt(card.getValue());
            }
        }
        return sum % 10;
    }

    @Override
    public void addToHand(Player player, Card card) {
        player.addToHand(card);
    }

    @Override
    public void addToHand(Player player, List<Card> cards) {
        for (Card card : cards) {
            player.addToHand(card);
        }
    }

    @Override
    public String getDeck() {
        return deckId;
    }
}
