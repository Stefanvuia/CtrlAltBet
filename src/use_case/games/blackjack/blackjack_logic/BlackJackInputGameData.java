package use_case.games.blackjack.blackjack_logic;

import entity.game_logic.BlackJackGame;
import entity.game_logic.BlackJackGameInterface;

/**
 * Represents input data for processing actions in a Blackjack game.
 */
public class BlackJackInputGameData {

    /**
     * The interface representing the state of the Blackjack game.
     */
    private final BlackJackGameInterface blackJackGameInterface;

    /**
     * Constructs a BlackJackInputGameData object with the specified Blackjack game interface.
     *
     * @param blackJackGameInterface The interface representing the state of the Blackjack game.
     */
    public BlackJackInputGameData(BlackJackGameInterface blackJackGameInterface) {
        this.blackJackGameInterface = blackJackGameInterface;
    }

    /**
     * Retrieves the Blackjack game interface representing the state of the game.
     *
     * @return The Blackjack game interface.
     */
    public BlackJackGameInterface getGame() {
        return this.blackJackGameInterface;
    }
}
