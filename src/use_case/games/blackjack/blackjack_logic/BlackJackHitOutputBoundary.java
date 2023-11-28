package use_case.games.blackjack.blackjack_logic;

public interface BlackJackHitOutputBoundary {
    void prepareContinueView(BlackJackOutputGameData outputGameData);

    void prepareLoseView(BlackJackOutputGameData outputGameData);
}
