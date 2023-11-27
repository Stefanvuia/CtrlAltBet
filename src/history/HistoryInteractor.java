package history;

import org.knowm.xchart.style.Styler;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;


import java.util.ArrayList;
import java.util.List;


//public class HistoryInteractor extends JPanel {
//
//    public HistoryInteractor() {
//        super(new BorderLayout());
//        // Create Chart here using XChart and add it to this panel
//        XYChart chart = createChart();
//        // Create and set up the chart panel
//        XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
//        this.add(chartPanel, BorderLayout.CENTER);
//    }
//
//        private XYChart createChart() {
//            // Create Chart
//            XYChart chart = new XYChartBuilder().width(800).height(600).title("Sample Chart").xAxisTitle("X").yAxisTitle("Y").build();
//
//            // Customize Chart
//            chart.getStyler().setChartTitleVisible(true);
//            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
//
//            // Series
//            double[] xData = new double[]{0.0, 1.0, 2.0, 3.0, 4.0};
//            double[] yData = new double[]{2.0, 1.0, 0.0, 1.0, 2.0};
//
//            chart.addSeries("Sample Series", xData, yData);
//
//            return chart;
//        }
//
//}
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

            // TODO ignore the first entry of payouts
            List<Integer> xData = new ArrayList<>();
            for (int i = 0; i < payouts.size(); i++) {
                xData.add(i + 1);
            }

            // Create Chart
            XYChart chart = new XYChartBuilder().width(800).height(600).title(game + " Payout History").xAxisTitle("Game").yAxisTitle("Payout").build();

            // Customize Chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);

            // Series
            XYSeries series = chart.addSeries("Payouts", xData, payouts);
            series.setMarker(SeriesMarkers.CIRCLE);

            HistoryOutputData outputData = new HistoryOutputData(chart);
            historyPresenter.presentHistoryChart(outputData);
        }
    }
}