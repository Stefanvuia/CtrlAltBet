package use_case.games.baccarat;

import entity.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents output data for a Baccarat game, including messages, ending funds, and player and banker hands.
 */
public class BaccaratOutputData {

    /**
     * The message conveying information about the Baccarat game outcome.
     */
    private final String message;

    /**
     * The ending funds after the Baccarat game.
     */
    private final int endFunds;

    /**
     * The list of cards in the player's hand.
     */
    private List<Card> playerHand = new ArrayList<>();

    /**
     * The list of cards in the banker's hand.
     */
    private List<Card> bankerHand = new ArrayList<>();

    /**
     * Constructs a BaccaratOutputData object with the specified message and ending funds.
     *
     * @param message  The message conveying information about the Baccarat game outcome.
     * @param endFunds The ending funds after the Baccarat game.
     */
    public BaccaratOutputData(String message, int endFunds) {
        this.message = message;
        this.endFunds = endFunds;
    }

    /**
     * Constructs a BaccaratOutputData object with the specified message, ending funds, player hand, and banker hand.
     *
     * @param message      The message conveying information about the Baccarat game outcome.
     * @param endFunds     The ending funds after the Baccarat game.
     * @param playerHand   The list of cards in the player's hand.
     * @param bankerHand   The list of cards in the banker's hand.
     */
    public BaccaratOutputData(String message, int endFunds, List<Card> playerHand, List<Card> bankerHand) {
        this.message = message;
        this.endFunds = endFunds;
        this.playerHand = playerHand;
        this.bankerHand = bankerHand;
    }

    /**
     * Retrieves the message conveying information about the Baccarat game outcome.
     *
     * @return The Baccarat game outcome message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the ending funds after the Baccarat game.
     *
     * @return The ending funds.
     */
    public int getEndFunds() {
        return endFunds;
    }

    /**
     * Retrieves the list of cards in the player's hand.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * Retrieves the list of cards in the banker's hand.
     *
     * @return The list of cards in the banker's hand.
     */
    public List<Card> getBankerHand() {
        return bankerHand;
    }
}
