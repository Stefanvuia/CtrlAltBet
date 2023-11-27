package interface_adapter.history;

import org.knowm.xchart.XYChart;

import java.util.*;

public class HistoryState {

    // TODO not sure if its null
    private Object chart = null;

    public HistoryState(HistoryState copy) {
        chart = copy.chart;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HistoryState(){
    }

    // TODO not sure if it should be casted here or in HistoryView
    public XYChart getChart() {
        return (XYChart) chart;
    }

    public void setChart(XYChart chart) {
        this.chart = chart;
    }




}
