package use_case.baccarat;

public interface BaccaratOutputBoundary {
    void preparePayoutView(BaccaratOutputData baccaratOutputData);

    void prepareFailView(BaccaratOutputData baccaratOutputData);
}
