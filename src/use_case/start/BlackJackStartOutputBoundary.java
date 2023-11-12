package use_case.start;

public interface BlackJackStartOutputBoundary {
    void prepareSuccessView(BlackJackStartOutputData outputData);

    void prepareFailView(String error);
}
