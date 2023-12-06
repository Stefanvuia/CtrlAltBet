package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a Baccarat game with specific functionality for the game.
 */
public class BaccaratPlayer extends Player {

    /**
     * Constructs a Baccarat player with an empty hand.
     */
    public BaccaratPlayer() {
        this.hands = new ArrayList<Card>();
    }

    /**
     * Retrieves the hand of the Baccarat player.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getHand() {
        return super.getHand();
    }

    /**
     * Adds a card to the hand of the Baccarat player.
     *
     * @param card The card to be added to the player's hand.
     */
    public void addToHand(Card card) {
        super.addToHand(card);
    }
}
