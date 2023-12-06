package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dealer in a War game with specific functionality for the game.
 */
public class WarDealer extends Player{
    /**
     * Constructs a War dealer with an empty hand.
     */
    public WarDealer(){
        this.hands = new ArrayList<Card>();
    }
    /**
     * Retrieves the hand of the War dealer.
     *
     * @return The list of cards in the dealer's hand.
     */
    public List<Card> getHand() {
        return super.getHand();
    }

    /**
     * Adds a card to the hand of the War dealer.
     *
     * @param card The card to be added to the dealer's hand.
     */
    public void addToHand(Card card) {
        super.addToHand(card);
    }
}
