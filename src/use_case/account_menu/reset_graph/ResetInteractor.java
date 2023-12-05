package use_case.account_menu.reset_graph;

import java.io.IOException;

public class ResetInteractor implements ResetInputBoundary {

    final ResetDataAccessInterface historyDAO;

    public ResetInteractor(ResetDataAccessInterface historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Override
    public void resetGraph(ResetInputData resetInputData) throws IOException {
        historyDAO.reset(resetInputData.getUsername());
    }
}
