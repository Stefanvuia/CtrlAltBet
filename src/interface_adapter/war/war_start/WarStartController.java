package interface_adapter.war.war_start;

import use_case.games.war.war_started.WarStartInputBoundary;
import use_case.games.war.war_started.WarStartInputData;

public class WarStartController {
    final WarStartInputBoundary startUseCaseInteractor;

    public WarStartController(WarStartInputBoundary startUseCaseInteractor) {
        this.startUseCaseInteractor = startUseCaseInteractor;
    }

    public void execute(String username, int bet) {
        WarStartInputData startInputData = new WarStartInputData(username, bet);

        startUseCaseInteractor.execute(startInputData);
    }
}
