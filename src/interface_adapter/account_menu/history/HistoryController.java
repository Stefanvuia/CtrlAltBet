package interface_adapter.account_menu.history;


import use_case.account_menu.history.HistoryInputBoundary;
import use_case.account_menu.history.HistoryInputData;

/**
 * Controller for handling all games history/statistics in the account menu.
 */
public class HistoryController {

    final HistoryInputBoundary userHistoryUseCaseInterator;

    /**
     * Constructs a HistoryController with the given HistoryInputBoundary.
     *
     * @param userHistoryUseCaseInterator The interactor that implements the HistoryInputBoundary,
     *                                    responsible for creating the user's game history chart.
     */
    public HistoryController(HistoryInputBoundary userHistoryUseCaseInterator) {
        this.userHistoryUseCaseInterator = userHistoryUseCaseInterator;
    }

    /**
     * Executes the action to create a chart based on the user's game history.
     *
     * @param username The username of the user whose game history is to be charted.
     * @param game     The name of the game for which the history chart is to be created.
     */
    public void execute(String username, String game) {
        HistoryInputData historyInputData = new HistoryInputData(username, game);

        userHistoryUseCaseInterator.createChart(historyInputData);
    }
}
