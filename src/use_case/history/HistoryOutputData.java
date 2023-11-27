package use_case.history;

import org.knowm.xchart.XYChart;

public class HistoryOutputData {
    private final XYChart chart;

    public HistoryOutputData(XYChart chart) {
        this.chart = chart;
    }

    public XYChart getChart() {
        return chart;
    }
}
