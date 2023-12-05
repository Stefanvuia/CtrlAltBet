package interface_adapter.account_menu.reset_graph;

import use_case.account_menu.reset_graph.ResetInputBoundary;
import use_case.account_menu.reset_graph.ResetInputData;

import java.io.IOException;

public class ResetController {
    final ResetInputBoundary resetInteractor;

    public ResetController(ResetInputBoundary resetInteractor) {
        this.resetInteractor = resetInteractor;
    }

    public void execute(String username) throws IOException {
        ResetInputData resetInputData = new ResetInputData(username);
        resetInteractor.resetGraph(resetInputData);
    }
}
