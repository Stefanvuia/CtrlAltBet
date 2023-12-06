package interface_adapter.account_menu.history;

import org.knowm.xchart.XYChart;

public class HistoryState {

    private XYChart chart = null;

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HistoryState() {
    }

    public XYChart getChart() {
        return chart;
    }

    public void setChart(XYChart chart) {
        this.chart = chart;
    }


}
