package interface_adapter.games.war;

import entity.game_logic.WarGameInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The WarGameState class represents the state of the War card game during gameplay. It includes
 * information such as the current bet, game messages, images of player and dealer cards, the
 * WarGameInterface, and flags indicating whether the player surrendered or went to war. Additionally,
 * it provides methods to retrieve and modify these attributes.
 */
public class WarGameState {

    /**
     * The current bet in the War card game.
     */
    private int bet = 0;

    /**
     * The list of images representing the player's cards.
     */
    private java.util.List<Image> playerImages = new ArrayList<>();

    /**
     * The list of images representing the dealer's cards.
     */
    private java.util.List<Image> dealerImages = new ArrayList<>();

    /**
     * The interface representing the overall state of the War card game.
     */
    private WarGameInterface warGameInterface = null;

    /**
     * Flag indicating whether the player has surrendered.
     */
    private boolean surrendered = false;

    /**
     * Flag indicating whether the player went to war.
     */
    private boolean wentToWar = false;

    /**
     * Error message related to the game state.
     */
    private String errorMessage = null;

    /**
     * Constructs a new WarGameState with default values.
     */
    public WarGameState() {
    }

    /**
     * Sets the wentToWar flag indicating whether the player went to war.
     *
     * @param wentToWar The flag indicating if the player went to war.
     */
    public void setDidGoToWar(boolean wentToWar) {
        this.wentToWar = wentToWar;
    }

    /**
     * Retrieves the surrendered flag indicating whether the player has surrendered.
     *
     * @return true if the player surrendered, false otherwise.
     */
    public boolean getSurrendered() {
        return surrendered;
    }

    /**
     * Sets the surrendered flag indicating whether the player has surrendered.
     *
     * @param surrendered The flag indicating surrender status.
     */
    public void setSurrendered(boolean surrendered) {
        this.surrendered = surrendered;
    }

    /**
     * Retrieves the wentToWar flag indicating whether the player went to war.
     *
     * @return true if the player went to war, false otherwise.
     */
    public boolean getWentToWar() {
        return wentToWar;
    }

    /**
     * Retrieves the error message related to the game state.
     *
     * @return The error message, or null if no error.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message related to the game state.
     *
     * @param message The error message to set.
     */
    public void setErrorMessage(String message) {
        errorMessage = message;
    }

    /**
     * Retrieves the current bet in the War card game.
     *
     * @return The current bet amount.
     */
    public int getBet() {
        return bet;
    }

    /**
     * Sets the current bet in the War card game.
     *
     * @param bet The new bet amount to set.
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Retrieves the WarGameInterface representing the overall state of the War card game.
     *
     * @return The WarGameInterface instance.
     */
    public WarGameInterface getGame() {
        return warGameInterface;
    }

    /**
     * Sets the WarGameInterface representing the overall state of the War card game.
     *
     * @param warGameInterface The new WarGameInterface instance to set.
     */
    public void setGame(WarGameInterface warGameInterface) {
        this.warGameInterface = warGameInterface;
    }

    /**
     * Retrieves the list of images representing the player's cards.
     *
     * @return The list of player card images.
     */
    public java.util.List<Image> getPlayerImages() {
        return playerImages;
    }

    /**
     * Sets the list of images representing the player's cards.
     *
     * @param playerImages The new list of player card images to set.
     */
    public void setPlayerImages(java.util.List<Image> playerImages) {
        this.playerImages = playerImages;
    }

    /**
     * Retrieves the list of images representing the dealer's cards.
     *
     * @return The list of dealer card images.
     */
    public java.util.List<Image> getDealerImages() {
        return dealerImages;
    }

    /**
     * Sets the list of images representing the dealer's cards.
     *
     * @param dealerImages The new list of dealer card images to set.
     */
    public void setDealerImages(List<Image> dealerImages) {
        this.dealerImages = dealerImages;
    }
}
