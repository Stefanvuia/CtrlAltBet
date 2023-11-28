package use_case.games.war.war_started;

public interface WarStartOutputBoundary {
    void prepareSuccessView(WarStartOutputData outputData);

    void prepareFailView(String error);
}
