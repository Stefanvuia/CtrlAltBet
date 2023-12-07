package interface_adapter.games.baccarat;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of a Baccarat game for use in the user interface.
 */
public class BaccaratGameState {

    /**
     * List of images representing the player's hand in the Baccarat game.
     */
    private List<Image> playerHand = new ArrayList<>();

    /**
     * List of images representing the banker's hand in the Baccarat game.
     */
    private List<Image> bankerHand = new ArrayList<>();

    /**
     * A message providing information or updates about the current state of the Baccarat game.
     */
    private String gameMessage = "";

    /**
     * Constructs an empty BaccaratGameState.
     */
    public BaccaratGameState() {
    }

    /**
     * Gets the list of images representing the player's hand.
     *
     * @return The list of images representing the player's hand.
     */
    public List<Image> getPlayerHandImg() {
        return playerHand;
    }

    /**
     * Sets the list of images representing the player's hand.
     *
     * @param playerHand The list of images representing the player's hand.
     */
    public void setPlayerHand(List<Image> playerHand) {
        this.playerHand = playerHand;
    }

    /**
     * Gets the list of images representing the banker's hand.
     *
     * @return The list of images representing the banker's hand.
     */
    public List<Image> getBankerHandImg() {
        return bankerHand;
    }

    /**
     * Sets the list of images representing the banker's hand.
     *
     * @param bankerHand The list of images representing the banker's hand.
     */
    public void setBankerHand(List<Image> bankerHand) {
        this.bankerHand = bankerHand;
    }

    /**
     * Gets the game message providing information or updates about the current state of the Baccarat game.
     *
     * @return The game message.
     */
    public String getGameMessage() {
        return gameMessage;
    }

    /**
     * Sets the game message providing information or updates about the current state of the Baccarat game.
     *
     * @param gameMessage The game message.
     */
    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }
}
