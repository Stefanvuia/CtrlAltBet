package interface_adapter.history;

import org.knowm.xchart.XYChart;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

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
