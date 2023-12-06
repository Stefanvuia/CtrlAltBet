package use_case.games.baccarat;

import entity.cards.Card;
import entity.game_logic.BaccaratGame;
import entity.game_logic.BaccaratGameInterface;
import entity.game_logic.BaccaratPlayer;
import use_case.games.CardsAPIInterface;

import java.util.List;
import java.util.Set;

/**
 * Handles the game logic for a Baccarat game, including player actions and game outcomes.
 */
public class BaccaratGameLogic {

    /**
     * Interface for interacting with the Cards API.
     */
    private final CardsAPIInterface cardsAPI;

    /**
     * Player representing the user in the Baccarat game.
     */
    private BaccaratPlayer player;

    /**
     * Player representing the banker in the Baccarat game.
     */
    private BaccaratPlayer banker;

    /**
     * Interface representing the Baccarat game and its functionality.
     */
    private BaccaratGameInterface game;

    /**
     * Constructs a BaccaratGameLogic object with the specified Cards API interface.
     *
     * @param cardsAPI The interface for interacting with the Cards API.
     */
    public BaccaratGameLogic(CardsAPIInterface cardsAPI) {
        this.cardsAPI = cardsAPI;
    }

    /**
     * Helper method for dealing the third card in a Baccarat game and determining the winner.
     *
     * @return The outcome of the Baccarat game ("player", "banker", or "tie").
     */
    public String thirdCardHelper() {
        /*
         * Preconditions:
         * - The Baccarat game, player, and banker must be initialized.
         */
        if (game.sumHand(player) <= 5) {
            game.addToHand(player, cardsAPI.draw(game.getDeck()));

            if (game.sumHand(banker) <= 2) {
                game.addToHand(banker, cardsAPI.draw(game.getDeck()));
            } else if (game.sumHand(banker) <= 3 && game.sumHand(player) != 8) {
                game.addToHand(banker, cardsAPI.draw(game.getDeck()));
            } else if (game.sumHand(banker) <= 4 && Set.of(2, 3, 4, 5, 6, 7).contains(game.sumHand(player))) {
                game.addToHand(banker, cardsAPI.draw(game.getDeck()));
            } else if (game.sumHand(banker) <= 5 && Set.of(4, 5, 6, 7).contains(game.sumHand(player))) {
                game.addToHand(banker, cardsAPI.draw(game.getDeck()));
            } else if (game.sumHand(banker) <= 6 && Set.of(6, 7).contains(game.sumHand(player))) {
                game.addToHand(banker, cardsAPI.draw(game.getDeck()));
            }
        } else if (game.sumHand(banker) <= 5) {
            game.addToHand(banker, cardsAPI.draw(game.getDeck()));
        }

        if (game.sumHand(player) > game.sumHand(banker)) {
            return "player";
        } else if (game.sumHand(player) < game.sumHand(banker)) {
            return "banker";
        } else {
            return "tie";
        }
    }

    /**
     * Starts a new Baccarat game by initializing players, shuffling the deck, and dealing initial cards.
     */
    public void startGame() {
        /*
         * Postconditions:
         * - The player, banker, and game must be initialized with valid data.
         */
        banker = new BaccaratPlayer();
        player = new BaccaratPlayer();
        String deckId = cardsAPI.shuffleNew(8);

        game = new BaccaratGame(player, banker, deckId);
        game.addToHand(player, cardsAPI.draw(deckId, 2));
        game.addToHand(banker, cardsAPI.draw(deckId, 2));
    }

    /**
     * Retrieves the current hand of the player in the Baccarat game.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getPlayerHand() {
        /*
         * Preconditions:
         * - The Baccarat game, player, and banker must be initialized.
         */
        return player.getHand();
    }

    /**
     * Retrieves the current hand of the banker in the Baccarat game.
     *
     * @return The list of cards in the banker's hand.
     */
    public List<Card> getBankerHand() {
        /*
         * Preconditions:
         * - The Baccarat game, player, and banker must be initialized.
         */
        return banker.getHand();
    }
}
