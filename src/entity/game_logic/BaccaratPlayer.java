package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a Baccarat game with specific functionality for the game.
 */
public class BaccaratPlayer implements Player {
    private final List<Card> hands;

    /**
     * Constructs a Baccarat player with an empty hand.
     */
    public BaccaratPlayer() {
        this.hands = new ArrayList<>();
    }

    /**
     * Retrieves the hand of the Baccarat player.
     *
     * @return The list of cards in the player's hand.
     */
    @Override
    public List<Card> getHand() {
        return hands;
    }

    /**
     * Adds a card to the hand of the Baccarat player.
     *
     * @param card The card to be added to the player's hand.
     */
    @Override
    public void addToHand(Card card) {
        hands.add(card);
    }
}
