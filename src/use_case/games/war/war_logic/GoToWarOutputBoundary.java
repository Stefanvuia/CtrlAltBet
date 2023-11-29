package use_case.games.war.war_logic;


import use_case.games.blackjack.blackjack_logic.BlackJackOutputGameData;

public interface GoToWarOutputBoundary {
    void preparePayoutView(GoToWarOutputData goToWarOutputData);

    void prepareFailView(GoToWarOutputData goToWarOutputData);
}
