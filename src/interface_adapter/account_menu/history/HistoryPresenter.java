package interface_adapter.account_menu.history;

import use_case.account_menu.history.HistoryOutputBoundary;
import use_case.account_menu.history.HistoryOutputData;
import org.knowm.xchart.XYChart;

public class HistoryPresenter implements HistoryOutputBoundary {

    private final HistoryViewModel historyViewModel;

    public HistoryPresenter(HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
    }

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
