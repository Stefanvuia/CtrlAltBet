package use_case.games.baccarat;

import entity.game_logic.BaccaratGame;
import entity.game_logic.BaccaratGameInterface;
import entity.game_logic.BaccaratPlayer;
import entity.game_logic.Player;
import use_case.games.CardsAPIInterface;
import use_case.games.GameDataAccessInterface;

import java.util.Map;
import java.util.Set;

public class BaccaratInteractor implements BaccaratInputBoundary {
    final CardsAPIInterface cardsAPI;

    final GameDataAccessInterface gameDAO;

    final BaccaratOutputBoundary baccaratPresenter;

    public BaccaratInteractor(CardsAPIInterface cardsAPI,
                              GameDataAccessInterface gameDAO,
                              BaccaratOutputBoundary baccaratPresenter) {
        this.cardsAPI = cardsAPI;
        this.gameDAO = gameDAO;
        this.baccaratPresenter = baccaratPresenter;
    }

    @Override
    public void execute(BaccaratInputData baccaratInputData) {
        String username = baccaratInputData.getUsername();
        Map<String, Integer> bet = baccaratInputData.getBet();

        if (checkBet(username, bet)) {
            Player banker = new BaccaratPlayer();
            Player player = new BaccaratPlayer();
            String deckId = cardsAPI.shuffleNew(8);

            BaccaratGameInterface game = new BaccaratGame(player, banker, deckId);
            game.addToHand(player, cardsAPI.draw(deckId, 2));
            game.addToHand(banker, cardsAPI.draw(deckId, 2));

            String winner = thirdCardHelper(game, player, banker);
            int payout = payoutHelper(username, bet, winner);

            BaccaratOutputData outputData = new BaccaratOutputData(
                    winner.toUpperCase() + " win. You win " + payout + "!",
                    gameDAO.getFund(username),
                    player.getHand(),
                    banker.getHand());
            baccaratPresenter.preparePayoutView(outputData);
        } else {
            BaccaratOutputData outputData = new BaccaratOutputData("Insufficient funds!", gameDAO.getFund(username));
            baccaratPresenter.prepareFailView(outputData);
        }
    }

    private boolean checkBet(String username, Map<String, Integer> bet) {
        int sum = 0;
        for (String key : bet.keySet()) {
            sum += bet.get(key);
        }
        if (gameDAO.getFund(username) >= sum) {
            gameDAO.editFund(username, -sum);
            return true;
        } else {
            return false;
        }
    }

    private String thirdCardHelper(BaccaratGameInterface game, Player player, Player banker) {
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

    private int payoutHelper(String username, Map<String, Integer> bet, String winner) {
        int payout;
        if (winner.equals("tie")) {
            payout = bet.get(winner) * 8;
        } else if (winner.equals("banker")) {
            payout = (int) (bet.get(winner) * 0.95);
        } else {
            payout = bet.get(winner);
        }

        gameDAO.editFund(username, payout + bet.get(winner));
        return payout;
    }
}
