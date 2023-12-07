package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dealer in a BlackJack game with specific functionality for the game.
 */
public class BlackJackDealer implements Player {
    private final List<Card> hands;

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
    @Override
    public List<Card> getHand() {
        return hands;
    }

    /**
     * Adds a card to the hand of the BlackJack dealer.
     *
     * @param card The card to be added to the dealer's hand.
     */
    @Override
    public void addToHand(Card card) {
        hands.add(card);
    }
}
