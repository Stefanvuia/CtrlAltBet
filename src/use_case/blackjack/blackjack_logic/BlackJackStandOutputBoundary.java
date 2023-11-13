package use_case.blackjack.blackjack_logic;

public interface BlackJackStandOutputBoundary {
    void prepareWinView(BlackJackOutputGameData outputGameData);

    void prepareLoseView(BlackJackOutputGameData outputGameData);

    void preparePushView(BlackJackOutputGameData outputGameData);
}
