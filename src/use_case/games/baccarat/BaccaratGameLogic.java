package use_case.games.baccarat;

import entity.Card;
import entity.game_logic.BaccaratGame;
import entity.game_logic.BaccaratGameInterface;
import entity.game_logic.BaccaratPlayer;
import use_case.games.CardsAPIInterface;

import java.util.List;
import java.util.Set;

public class BaccaratGameLogic {
    private CardsAPIInterface cardsAPI;

    public BaccaratGameLogic(CardsAPIInterface cardsAPI) {
        this.cardsAPI = cardsAPI;
    }

    private BaccaratPlayer player;

    private BaccaratPlayer banker;

    private BaccaratGameInterface game;

    public String thirdCardHelper() {
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

    public void startGame() {
        banker = new BaccaratPlayer();
        player = new BaccaratPlayer();
        String deckId = cardsAPI.shuffleNew(8);

        game = new BaccaratGame(player, banker, deckId);
        game.addToHand(player, cardsAPI.draw(deckId, 2));
        game.addToHand(banker, cardsAPI.draw(deckId, 2));
    }

    public List<Card> getPlayerHand() {
        return player.getHand();
    }

    public List<Card> getBankerHand() {
        return banker.getHand();
    }
}
