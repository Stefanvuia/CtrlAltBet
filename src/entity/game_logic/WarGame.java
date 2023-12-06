package entity.game_logic;

import entity.cards.Card;

import java.util.List;
/**
 * Represents a War game with user, dealer, and deck information.
 */
public class WarGame implements WarGameInterface {
    /**
     * Player object representing the user in the War game.
     */
    private final Player user;
    /**
     * Player object representing the dealer in the War game.
     */
    private final Player dealer;
    /**
     * Unique identifier for the deck used in the War game.
     */
    private final String deckId;

    /**
     * Constructs a War game with the specified user, dealer, and deck.
     *
     * @param user    The user in the War game.
     * @param dealer  The dealer in the War game.
     * @param deckId  The unique identifier for the deck used in the War game.
     */
    public WarGame(Player user, Player dealer, String deckId){
        this.user = user;
        this.dealer = dealer;
        this.deckId = deckId;
    }
    /**
     * Calculates and returns the value of the last card in the Player or Dealer's hand.
     *
     * @param player The player whose hand sum is to be calculated.
     * @return The sum of hand values for the specified player in War.
     */
    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int value;
        Card card = hand.get(hand.size() - 1);
        if (card.getValue().equals("JACK")){
            value = 11;
        } else if (card.getValue().equals("QUEEN")) {
            value = 12;
        } else if (card.getValue().equals("KING")) {
            value = 13;
        } else if (card.getValue().equals("ACE")) {
            value = 14;
        } else {
            value = Integer.parseInt(card.getValue());
        }
        return value;
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
     * Determines whether the user goes to war with the dealer
     *
     * @return True if the player and dealer have the same last card, false otherwise.
     */
    @Override
    public boolean goToWar() {
        return sumHand(this.user) == sumHand(this.dealer);
    }

    /**
     * Determines whether the user wins the War game based on hand values.
     *
     * @return True if the user wins, false otherwise.
     */
    @Override
    public boolean playerWins() {
        return sumHand(this.user) > sumHand(this.dealer);
    }

    /**
     * Retrieves the player object representing the user in the War game.
     *
     * @return The user player object.
     */
    @Override
    public Player getPlayer() {
        return user;
    }

    /**
     * Retrieves the player object representing the dealer in the War game.
     *
     * @return The dealer player object.
     */
    @Override
    public Player getDealer() {
        return dealer;
    }

    /**
     * Retrieves the unique identifier of the deck used in the War game.
     *
     * @return The deck identifier.
     */
    @Override
    public String getDeck() {
        return deckId;
    }
}
