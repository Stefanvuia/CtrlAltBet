package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dealer in a BlackJack game with specific functionality for the game.
 */
public class BlackJackDealer extends Player {

    /**
     * Constructs a BlackJack dealer with an empty hand.
     */
    public BlackJackDealer() {
        this.hands = new ArrayList<>();
    }

    /**
     * Retrieves the hand of the BlackJack dealer.
     *
     * @return The list of cards in the dealer's hand.
     */
    public List<Card> getHand() {
        return super.getHand();
    }

    /**
     * Adds a card to the hand of the BlackJack dealer.
     *
     * @param card The card to be added to the dealer's hand.
     */
    public void addToHand(Card card) {
        super.addToHand(card);
    }
}
