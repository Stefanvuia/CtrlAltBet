package entity.game_logic;

import entity.cards.Card;

import java.util.List;

/**
 * Represents a BlackJack game with user, dealer, and deck information.
 */
public class BlackJackGame implements BlackJackGameInterface {

    /**
     * Player object representing the user in the BlackJack game.
     */
    private final Player user;

    /**
     * Player object representing the dealer in the BlackJack game.
     */
    private final Player dealer;

    /**
     * Unique identifier for the deck used in the BlackJack game.
     */
    private final String deckId;

    /**
     * Constructs a BlackJack game with the specified user, dealer, and deck.
     *
     * @param user   The user in the BlackJack game.
     * @param dealer The dealer in the BlackJack game.
     * @param deckId The unique identifier for the deck used in the BlackJack game.
     */
    public BlackJackGame(Player user, Player dealer, String deckId) {
        this.user = user;
        this.dealer = dealer;
        this.deckId = deckId;
    }

    /**
     * Calculates and returns the sum of the hand values for the given player in BlackJack.
     *
     * @param player The player whose hand sum is to be calculated.
     * @return The sum of hand values for the specified player in BlackJack.
     */
    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int aces = 0;
        int sum = 0;
        for (Card card : hand) {
            if (card.getValue().equals("ACE") && aces > 0) {
                aces++;
                sum++;
            } else if (card.getValue().equals("ACE")) {
                aces++;
                sum += 11;
            } else if (
                    card.getValue().equals("JACK") || card.getValue().equals("QUEEN") || card.getValue().equals("KING")
            ) {
                sum += 10;
            } else {
                sum += Integer.parseInt(card.getValue());
            }
        }

        if (sum > 21 && aces > 0) {
            return (sum - 10);
        } else {
            return sum;
        }
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
     * Determines whether the user wins the BlackJack game based on hand values.
     *
     * @return True if the user wins, false otherwise.
     */
    @Override
    public boolean userWin() {
        return (sumHand(user) > sumHand(dealer) && sumHand(user) <= 21) || sumHand(dealer) > 21;
    }

    /**
     * Retrieves the player object representing the user in the BlackJack game.
     *
     * @return The user player object.
     */
    @Override
    public Player getPlayer() {
        return user;
    }

    /**
     * Retrieves the player object representing the dealer in the BlackJack game.
     *
     * @return The dealer player object.
     */
    @Override
    public Player getDealer() {
        return dealer;
    }

    /**
     * Retrieves the unique identifier of the deck used in the BlackJack game.
     *
     * @return The deck identifier.
     */
    @Override
    public String getDeck() {
        return deckId;
    }
}
