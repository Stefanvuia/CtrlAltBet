package entity.game_logic;

import entity.cards.Card;

import java.util.List;
import java.util.Set;

/**
 * Represents a Baccarat game with player, banker, and deck information.
 */
public class BaccaratGame implements BaccaratGameInterface {

    /**
     * Set of card values that are considered as 0 in Baccarat.
     */
    private final Set<String> VALUES = Set.of("JACK", "QUEEN", "KING", "10");

    /**
     * Player object representing the player in the game.
     */
    private final Player player;

    /**
     * Player object representing the banker in the game.
     */
    private final Player banker;

    /**
     * Unique identifier for the deck used in the game.
     */
    private final String deckId;

    /**
     * Constructs a Baccarat game with the specified player, banker, and deck.
     *
     * @param player  The player in the game.
     * @param banker  The banker in the game.
     * @param deckId  The unique identifier for the deck used in the game.
     */
    public BaccaratGame(Player player, Player banker, String deckId) {
        this.player = player;
        this.banker = banker;
        this.deckId = deckId;
    }

    /**
     * Calculates and returns the sum of the hand values for the given player.
     *
     * @param player The player whose hand sum is to be calculated.
     * @return The sum of hand values for the specified player.
     */
    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int sum = 0;
        for (Card card : hand) {
            if (card.getValue().equals("ACE")) {
                sum++;
            } else if (VALUES.contains(card.getValue())) {
                // For face cards (JACK, QUEEN, KING, 10), no value is added to the sum.
            } else {
                sum += Integer.parseInt(card.getValue());
            }
        }
        return sum % 10;
    }

    /**
     * Adds a single card to the hand of the specified player.
     *
     * @param player The player to whom the card is added.
     * @param card   The card to be added to the player's hand.
     */
    @Override
    public void addToHand(Player player, Card card) {
        player.addToHand(card);
    }

    /**
     * Adds a list of cards to the hand of the specified player.
     *
     * @param player The player to whom the cards are added.
     * @param cards  The list of cards to be added to the player's hand.
     */
    @Override
    public void addToHand(Player player, List<Card> cards) {
        for (Card card : cards) {
            player.addToHand(card);
        }
    }

    /**
     * Retrieves the unique identifier of the deck used in the game.
     *
     * @return The deck identifier.
     */
    @Override
    public String getDeck() {
        return deckId;
    }
}
