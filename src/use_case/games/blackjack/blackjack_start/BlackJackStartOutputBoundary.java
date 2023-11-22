package use_case.games.blackjack.blackjack_start;

public interface BlackJackStartOutputBoundary {
    void prepareSuccessView(BlackJackStartOutputData outputData);

    void prepareFailView(String error);
}
