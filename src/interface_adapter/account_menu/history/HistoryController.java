package interface_adapter.account_menu.history;


import use_case.account_menu.history.HistoryInputBoundary;
import use_case.account_menu.history.HistoryInputData;

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
