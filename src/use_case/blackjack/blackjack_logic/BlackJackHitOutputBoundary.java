package use_case.blackjack.blackjack_logic;

public interface BlackJackHitOutputBoundary {
    void prepareContinueView(BlackJackOutputGameData outputGameData);

    void prepareLoseView(BlackJackOutputGameData outputGameData);
}
