package use_case.blackjack.blackjack_start;

public interface BlackJackStartOutputBoundary {
    void prepareSuccessView(BlackJackStartOutputData outputData);

    void prepareFailView(String error);
}
