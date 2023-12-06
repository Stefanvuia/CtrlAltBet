package use_case.account_menu.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HistoryInteractorTest {

    private HistoryInputBoundary historyInteractor;

    @Mock
    private HistoryDataAccessInterface historyDAO;

    @Mock
    private HistoryOutputBoundary historyPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        historyInteractor = new HistoryInteractor(historyDAO, historyPresenter);
    }

    @Test
    void successChartCreation() {
        HistoryInputData historyInputData = new HistoryInputData("Luisa", "blackjack");

        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
            @Override
            public void presentHistoryChart(HistoryOutputData historyOutputData) {
                assertSame(historyOutputData.getChart(), new XYChartBuilder().width(800).height(600).build());
                assertTrue(historyDAO.existsByName("Luisa"));
            }

        };
        historyInteractor.createChart(historyInputData);
    }

    @Test
    void chartCustomizationTest() {
        String username = "Luisa";
        String game = "blackjack";
        HistoryInputData inputData = new HistoryInputData(username, game);
        ArrayList<Double> mockPayouts = new ArrayList<>(List.of(-100.0, 200.0, -50.0, 50.0));

        when(historyDAO.existsByName(username)).thenReturn(true);
        when(historyDAO.getPayouts(username, game)).thenReturn(mockPayouts);

        historyInteractor.createChart(inputData);

        verify(historyPresenter).presentHistoryChart(argThat(outputData -> {
            XYChart chart = outputData.getChart();
            assertNotNull(chart);

            assertEquals(new Color(100, 255, 100, 90), chart.getSeriesMap().get("Up by").getFillColor());
            assertEquals(new Color(255, 100, 100, 90), chart.getSeriesMap().get("Down by").getFillColor());

            assertEquals(SeriesMarkers.CIRCLE, chart.getSeriesMap().get("Up by").getMarker());
            assertEquals(SeriesMarkers.CIRCLE, chart.getSeriesMap().get("Down by").getMarker());

            assertFalse(chart.getStyler().isLegendVisible());
            assertTrue(chart.getStyler().isCursorEnabled());

            return true;
        }));
    }

    @Test
    void addsUserIfNotExistsTest() {
        String username = "Luisa";
        String game = "blackjack";
        HistoryInputData inputData = new HistoryInputData(username, game);

        when(historyDAO.existsByName(username)).thenReturn(false);
        historyInteractor.createChart(inputData);
        verify(historyDAO).addUser(username);
    }

    @Test
    void onlyZeroPayoutsTest() {
        String username = "Luisa";
        String game = "blackjack";
        HistoryInputData inputData = new HistoryInputData(username, game);
        ArrayList<Double> zeroPayouts = new ArrayList<>(Collections.nCopies(5, 0.0));

        when(historyDAO.existsByName(username)).thenReturn(true);
        when(historyDAO.getPayouts(username, game)).thenReturn(zeroPayouts);

        historyInteractor.createChart(inputData);

        verify(historyPresenter).presentHistoryChart(argThat(outputData -> {
            XYChart chart = outputData.getChart();
            assertNotNull(chart);

            XYSeries seriesAbove = chart.getSeriesMap().get("Up by");
            XYSeries seriesBelow = chart.getSeriesMap().get("Down by");

            assertNotNull(seriesAbove);
            assertNotNull(seriesBelow);

            assertTrue(Arrays.stream(seriesAbove.getYData()).allMatch(y -> y == 0.0));
            assertTrue(Arrays.stream(seriesBelow.getYData()).allMatch(y -> y == 0.0));

            return true;
        }));
    }
}
