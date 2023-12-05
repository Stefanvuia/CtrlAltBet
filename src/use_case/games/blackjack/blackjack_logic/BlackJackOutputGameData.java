package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGameInterface;

/**
 * Represents output data after processing an action in a Blackjack game, including game state, completion status, and funds change.
 */
public class BlackJackOutputGameData {

    /**
     * The interface representing the state of the Blackjack game.
     */
    private final BlackJackGameInterface blackJackGameInterface;

    /**
     * A boolean indicating whether the Blackjack game has finished after the action.
     */
    private final boolean gameFinished;

    /**
     * The change in funds after the action in the Blackjack game.
     */
    private final int change;

    /**
     * Constructs a BlackJackOutputGameData object with the specified Blackjack game interface, game completion status, and funds change.
     *
     * @param blackJackGameInterface The interface representing the state of the Blackjack game.
     * @param gameFinished           A boolean indicating whether the Blackjack game has finished after the action.
     * @param change                 The change in funds after the action in the Blackjack game.
     */
    public BlackJackOutputGameData(BlackJackGameInterface blackJackGameInterface, boolean gameFinished, int change) {
        this.blackJackGameInterface = blackJackGameInterface;
        this.gameFinished = gameFinished;
        this.change = change;
    }

    /**
     * Checks if the Blackjack game has finished after the action.
     *
     * @return True if the game has finished, false otherwise.
     */
    public boolean gameFinished() {
        return this.gameFinished;
    }

    /**
     * Retrieves the change in funds after the action in the Blackjack game.
     *
     * @return The change in funds.
     */
    public int getChange() {
        return this.change;
    }

    /**
     * Retrieves the interface representing the state of the Blackjack game.
     *
     * @return The Blackjack game interface.
     */
    public BlackJackGameInterface getGame() {
        return this.blackJackGameInterface;
    }
}
