package use_case.games.baccarat;

public interface BaccaratOutputBoundary {
    void preparePayoutView(BaccaratOutputData baccaratOutputData);

    void prepareFailView(BaccaratOutputData baccaratOutputData);
}
