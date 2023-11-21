package entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaccaratGame implements BaccaratGameInterface {
    private static final Set<String> VALUES = Set.of("JACK", "QUEEN", "KING", "10");
    private final Player player;
    private final Player banker;
    private final String deckId;

    private final Map<String, Integer> bet;

    public BaccaratGame(Player player, Player banker, String deckId, Map<String, Integer> bet) {
        this.player = player;
        this.banker = banker;
        this.deckId = deckId;
        this.bet = bet;
    }

    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int sum = 0;
        for (Card card : hand) {
            if (card.getValue().equals("ACE")) {
                sum++;
            } else if (VALUES.contains(card.getValue())) {
                break;
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
    public Map<String, Integer> getBet() {
        return bet;
    }

    @Override
    public String getDeck() {
        return deckId;
    }
}
