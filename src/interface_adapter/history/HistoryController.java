package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;
import view.HistoryView;


public class HistoryController {

    final HistoryInputBoundary userHistoryUseCaseInterator;

    public HistoryController(HistoryInputBoundary userHistoryUseCaseInterator) {
        this.userHistoryUseCaseInterator = userHistoryUseCaseInterator;
    }

    public void execute(String username, String game) {
        HistoryInputData historyInputData = new HistoryInputData(username, game);

        userHistoryUseCaseInterator.createChart(historyInputData);
    }
}
