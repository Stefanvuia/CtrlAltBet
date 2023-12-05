package interface_adapter.account_menu.history;

import use_case.account_menu.history.HistoryOutputBoundary;
import use_case.account_menu.history.HistoryOutputData;
import org.knowm.xchart.XYChart;

/**
 * Presenter for displaying user history charts.
 * Implements the HistoryOutputBoundary.
 */
public class HistoryPresenter implements HistoryOutputBoundary {

    private final HistoryViewModel historyViewModel;

    /**
     * Constructs a HistoryPresenter with a specified HistoryViewModel.
     *
     * @param historyViewModel The ViewModel that this presenter will update with chart data.
     */
    public HistoryPresenter(HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
    }

    /**
     * Presents the user history chart by updating the HistoryViewModel.
     * Takes the chart data from the HistoryOutputData and updates the ViewModel,
     * which in turn updates the view.
     *
     * @param historyOutputData The output data from the use case layer, containing the chart.
     */
    @Override
    public void presentHistoryChart(HistoryOutputData historyOutputData) {

        XYChart chart = historyOutputData.getChart();
        HistoryState historyState = historyViewModel.getHistoryState();
        // setting the state to the output chart
        historyState.setChart(chart);
        historyViewModel.setHistoryState(historyState);
        historyViewModel.firePropertyChanged();
    }
}
