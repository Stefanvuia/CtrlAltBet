package use_case.games.war.war_started;

public interface WarStartOutputBoundary {
    void prepareWarIngameView(WarStartOutputData outputData);
    void prepareGoToWarView(WarStartOutputData outputData);

    void prepareFailView(String error);
}
