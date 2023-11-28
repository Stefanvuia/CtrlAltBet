package use_case.account_menu.history;

import org.knowm.xchart.style.Styler;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;


import java.util.ArrayList;
import java.util.List;

public class HistoryInteractor implements HistoryInputBoundary {

    private HistoryDataAccessInterface historyDAO;
    private HistoryOutputBoundary historyPresenter;

    public HistoryInteractor(HistoryDataAccessInterface historyDataAccessInterface, HistoryOutputBoundary historyOutputBoundary) {
        this.historyDAO = historyDataAccessInterface;
        this.historyPresenter = historyOutputBoundary;

    }

    @Override
    public void createChart(HistoryInputData historyInputData) {
        if (historyDAO.existsByName(historyInputData.getUsername())) {
            String game = historyInputData.getGame();
            String username = historyInputData.getUsername();
            List<Double> payouts = historyDAO.getPayouts(username, game);

            List<Integer> xData = new ArrayList<>();
            List<Integer> yData = new ArrayList<>();
            int total = 0;
            for (int i = 0; i < payouts.size(); i++) {
                xData.add(i);

                total += payouts.get(i);
                yData.add(total);
            }

            // Create Chart
            XYChart chart = new XYChartBuilder().width(800).height(600).title(game.toUpperCase() + " Payout History").xAxisTitle("Game").yAxisTitle("Total Payout").build();

            // Customize Chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
//            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

            // Series
            XYSeries series = chart.addSeries("Total Payout", xData, yData);
            series.setMarker(SeriesMarkers.CIRCLE);

            HistoryOutputData outputData = new HistoryOutputData(chart);
            historyPresenter.presentHistoryChart(outputData);
        }
    }
}