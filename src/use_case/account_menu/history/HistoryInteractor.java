package use_case.account_menu.history;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

public class HistoryInteractor implements HistoryInputBoundary {

    final private HistoryDataAccessInterface historyDAO;
    final private HistoryOutputBoundary historyPresenter;

    public HistoryInteractor(HistoryDataAccessInterface historyDataAccessInterface, HistoryOutputBoundary historyOutputBoundary) {
        this.historyDAO = historyDataAccessInterface;
        this.historyPresenter = historyOutputBoundary;

    }

    /**
     * Creates a chart representing the payout history for a given user and game.
     * This method checks if the user's history exists in the database. If it does, it fetches
     * the payout data for the wanted game and creates an area chart with different colours
     * depending on if the data is above or below zero.
     *
     * @param historyInputData An object containing the username and game with getter methods for each respectively.
     *                         They are used to fetch the correct payout data.
     *
     * Note: This method does not return anything but is stored in the Output Data.
     *
     * Usage example:
     * createChart(new HistoryInputData("John", "blackjack"));
     */
    @Override
    public void createChart(HistoryInputData historyInputData) {
        if (historyDAO.existsByName(historyInputData.getUsername())) {

            String game = historyInputData.getGame();
            String username = historyInputData.getUsername();
            List<Double> payouts = historyDAO.getPayouts(username, game);

            List<Double> xDataAbove = new ArrayList<>();
            List<Double> yDataAbove = new ArrayList<>();
            List<Double> xDataBelow = new ArrayList<>();
            List<Double> yDataBelow = new ArrayList<>();

            double total = 0;
            double previousTotal = 0;

            for (int i = 0; i < payouts.size(); i++) {
                previousTotal = total;
                total += payouts.get(i);

                // Check if it crosses 0
                if ((previousTotal <= 0 && total > 0) || (previousTotal >= 0 && total < 0)) {
                    double zeroCrossX = i - 1 + Math.abs(previousTotal) / (Math.abs(total - previousTotal));
                    xDataAbove.add(zeroCrossX);
                    yDataAbove.add(0.0);
                    xDataBelow.add(zeroCrossX);
                    yDataBelow.add(0.0);
                }
                if (total == 0) {
                    xDataAbove.add((double)i);
                    yDataAbove.add(total);
                    xDataBelow.add((double)i);
                    yDataBelow.add(total);
                } else if (total < 0){
                    xDataBelow.add((double)i);
                    yDataBelow.add(total);
                } else {
                    xDataAbove.add((double)i);
                    yDataAbove.add(total);
                }
            }


            // Create Chart
            XYChart chart = new XYChartBuilder().width(800).height(600).title(game.toUpperCase() + " Payout History").xAxisTitle("Game").yAxisTitle("Total Payout").theme(Styler.ChartTheme.GGPlot2).build();

            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

            XYSeries seriesAbove = chart.addSeries("Up by", xDataAbove, yDataAbove);
            XYSeries seriesBelow = chart.addSeries("Down by", xDataBelow, yDataBelow);

            // Customize
            seriesAbove.setFillColor(new Color(100, 255, 100, 90)); // Green for above 0
            seriesBelow.setFillColor(new Color(255, 100, 100, 90)); // Red for below 0

            seriesAbove.setLineColor(new Color(100, 255, 100, 135));
            seriesBelow.setLineColor(new Color(255, 100, 100, 135));

            seriesAbove.setMarkerColor(new Color(100, 255, 100));
            seriesBelow.setMarkerColor(new Color(255, 100, 100));

            seriesAbove.setMarker(SeriesMarkers.CIRCLE);
            seriesBelow.setMarker(SeriesMarkers.CIRCLE);

            chart.getStyler().setLegendVisible(false);

            chart.getStyler().setCursorEnabled(true);
            chart.getStyler().setCustomCursorXDataFormattingFunction(x -> "Game " + (int) Math.round(x));

            HistoryOutputData outputData = new HistoryOutputData(chart);
            historyPresenter.presentHistoryChart(outputData);
        }
    }
}