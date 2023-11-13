package interface_adapter.blackjack.blackjack_logic;

import entity.Card;
import entity.Game;
import use_case.blackjack.blackjack_logic.BlackJackHitOutputBoundary;
import use_case.blackjack.blackjack_logic.BlackJackOutputGameData;
import use_case.blackjack.blackjack_logic.BlackJackStandOutputBoundary;

public class TestLogicPresenter implements BlackJackHitOutputBoundary, BlackJackStandOutputBoundary {
    private final TestLogicViewModel viewModel;

    public TestLogicPresenter(TestLogicViewModel a) {
        viewModel = a;
    }
    @Override
    public void prepareContinueView(BlackJackOutputGameData outputGameData) {
        Game game = outputGameData.getGame();
        boolean end = outputGameData.gameFinished();
        viewModel.setGameState(game);
        viewModel.setGameEnd(end);
        System.out.print("Player hand: ");
        for (Card card:
             game.getPlayer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getPlayer()));

        System.out.println("Dealer hand: " + game.getDealer().getHand().get(0).getValue());
    }

    @Override
    public void prepareWinView(BlackJackOutputGameData outputGameData) {
        Game game = outputGameData.getGame();
        boolean end = outputGameData.gameFinished();
        viewModel.setGameState(game);
        viewModel.setGameEnd(end);
        System.out.print("Player hand: ");
        for (Card card:
                game.getPlayer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getPlayer()));
        System.out.print("Dealer hand: ");
        for (Card card:
                game.getDealer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getDealer()) + " - PLAYER WIN");
    }

    @Override
    public void prepareLoseView(BlackJackOutputGameData outputGameData) {
        Game game = outputGameData.getGame();
        boolean end = outputGameData.gameFinished();
        viewModel.setGameState(game);
        viewModel.setGameEnd(end);
        System.out.print("Player hand: ");
        for (Card card:
                game.getPlayer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getPlayer()));
        System.out.print("Dealer hand: ");
        for (Card card:
                game.getDealer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getDealer()) + " - PLAYER LOSS");
    }

    @Override
    public void preparePushView(BlackJackOutputGameData outputGameData) {
        Game game = outputGameData.getGame();
        boolean end = outputGameData.gameFinished();
        viewModel.setGameState(game);
        viewModel.setGameEnd(end);
        System.out.print("Player hand: ");
        for (Card card:
                game.getPlayer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getPlayer()));
        System.out.print("Dealer hand: ");
        for (Card card:
                game.getDealer().getHand()) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("Sum: " + game.sumHand(game.getDealer()) + " - PUSH");
    }
}
