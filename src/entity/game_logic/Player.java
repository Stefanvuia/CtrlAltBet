package entity.game_logic;

import entity.cards.Card;

import java.util.List;

/**
 * Represents a player in a card game with a hand of cards.
 */
public abstract class Player {

    /**
     * The list of cards in the player's hand.
     */
    protected List<Card> hands;

    /**
     * Retrieves the hand of the player.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getHand() {
        return hands;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    void addToHand(Card card) {
        hands.add(card);
    }
}
