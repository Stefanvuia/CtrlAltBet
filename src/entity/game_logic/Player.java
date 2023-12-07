package entity.game_logic;

import entity.cards.Card;

import java.util.List;

/**
 * Represents a player in a card game with a hand of cards.
 */
public interface Player {

    /**
     * Retrieves the hand of the player.
     *
     * @return The list of cards in the player's hand.
     */
    List<Card> getHand();

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    void addToHand(Card card);
}
