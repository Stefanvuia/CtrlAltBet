package history;

import interface_adapter.UserViewModel;
import history.HistoryController;
import history.HistoryState;
import history.HistoryViewModel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The controller
     */
    private final JButton blackjack;
    private final JButton baccarat;
    private final JButton war;
    private final JButton back;
    private final UserViewModel userViewModel;
    private final HistoryController historyController;
    private final HistoryViewModel historyViewModel;

    /**
     * A window with a title and a JButton.
     */
    public HistoryView(UserViewModel userViewModel, HistoryViewModel historyViewModel, HistoryController historyController) {

        this.userViewModel = userViewModel;
        this.historyController = historyController;
        this.historyViewModel = historyViewModel;
        historyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Betting History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("See user's betting history of:");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        blackjack = new JButton(HistoryViewModel.BLACKJACK_BUTTON_LABEL);
        buttons.add(blackjack);
        baccarat = new JButton(HistoryViewModel.BACCARAT_BUTTON_LABEL);
        buttons.add(baccarat);
        war = new JButton(HistoryViewModel.WAR_BUTTON_LABEl);
        buttons.add(war);
        back = new JButton(HistoryViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        blackjack.addActionListener(this);
        baccarat.addActionListener(this);
        war.addActionListener(this);
        back.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(subtitle);
        this.add(buttons);


    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(back)) {
            userViewModel.setState(UserViewModel.LoginState.ACCOUNT_INFO);
        } else {
            // Assuming evt.getActionCommand() returns "blackjack", "baccarat", or "war"
            String username = userViewModel.getCurrentUser();
            historyController.execute(username, evt.getActionCommand());

        }
    }

    private void displayChart(XYChart chart) {
        // Create a new frame for the chart popup
        System.out.println("made it");
        JFrame chartFrame = new JFrame("Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(800, 600);

        // Add the chart to the frame
        XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
        chartFrame.add(chartPanel);

        // Display the frame
        chartFrame.setLocationRelativeTo(this); // To center it relative to the HistoryView
        chartFrame.setVisible(true);
    }

    // TODO tbh idk if this actually does what I want or if needed
//    private void updateChart(XYChart chart) {
//        // Remove the existing chart panel (if present)
//        chartPanel.removeAll();
//
//        // Create a new XChartPanel with the updated chart
//        XChartPanel<XYChart> newChartPanel = new XChartPanel<>(chart);
//
//        // Add the new chart panel to the chartPanel container
//        chartPanel.add(newChartPanel);
//        chartPanel.revalidate();
//        chartPanel.repaint();
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("history")) {
            // Update the view with the new chart data
            HistoryState state = historyViewModel.getHistoryState();
            XYChart chart = state.getChart();
            if (chart != null) {
                displayChart(chart);
            }
        }
    }
}
