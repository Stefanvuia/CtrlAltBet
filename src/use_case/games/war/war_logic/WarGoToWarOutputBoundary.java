package use_case.games.war.war_logic;


public interface WarGoToWarOutputBoundary {
    void preparePayoutView(WarOutputGameData warOutputGameData);

    void prepareFailView(String error);
}
