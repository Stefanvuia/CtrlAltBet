package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a BlackJack game with specific functionality for the game.
 */
public class BlackJackPlayer extends Player {

    /**
     * The amount of the bet placed by the BlackJack player.
     */
    private final int bet;

    /**
     * The username of the BlackJack player.
     */
    private final String username;

    /**
     * Constructs a BlackJack player with the specified bet amount and username.
     *
     * @param bet      The amount of the bet placed by the player.
     * @param username The username of the player.
     */
    public BlackJackPlayer(int bet, String username) {
        this.hands = new ArrayList<Card>();
        this.bet = bet;
        this.username = username;
    }

    /**
     * Retrieves the hand of the BlackJack player.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getHand() {
        return super.getHand();
    }

    /**
     * Adds a card to the hand of the BlackJack player.
     *
     * @param card The card to be added to the player's hand.
     */
    public void addToHand(Card card) {
        super.addToHand(card);
    }

    /**
     * Retrieves the amount of the bet placed by the BlackJack player.
     *
     * @return The amount of the bet.
     */
    public int getBet() {
        return this.bet;
    }

    /**
     * Retrieves the username of the BlackJack player.
     *
     * @return The username of the player.
     */
    public String getUsername() {
        return this.username;
    }
}
