package view;

import interface_adapter.account_menu.history.HistoryController;
import interface_adapter.account_menu.history.HistoryState;
import interface_adapter.account_menu.history.HistoryViewModel;
import interface_adapter.account_menu.AccountInfoState;
import interface_adapter.account_menu.AccountInfoViewModel;
import interface_adapter.account_menu.sign_out.SignOutController;
import interface_adapter.game_menu.exit.ExitController;
import interface_adapter.account_menu.update.UserUpdateController;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import tools.GridBagUtils;
import view.custom_elements.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class AccountInfoView extends JPanel implements ActionListener, PropertyChangeListener, DocumentListener {
    public String viewName = "account info";
    private final AccountInfoViewModel accountInfoViewModel;

    private final HistoryController historyController;

    private final HistoryViewModel historyViewModel;

    private final ExitController exitController;

    private final UserUpdateController updateController;

    private final SignOutController signOutController;

    private final JButton deposit;
    private final JButton withdraw;
    private final JButton signout;
    private final JButton exit;

    private JFormattedTextField depositField;

    private JFormattedTextField withdrawField;

    private JPanel fundsPanel;

    private JPanel userPanel;

    private JLabel fundsLabel;

    private JLabel userLabel;

    private JButton blackJackButton;

    private JButton baccararatButton;

    private JButton warButton;

    /**
     * A window with a title and a JButton.
     */
    public AccountInfoView(AccountInfoViewModel accountInfoViewModel,
                           ExitController exitController,
                           UserUpdateController updateController,
                           SignOutController signOutController,
                           HistoryController historyController,
                           HistoryViewModel historyViewModel) {
        this.exitController = exitController;
        this.updateController = updateController;
        this.signOutController = signOutController;
        this.accountInfoViewModel = accountInfoViewModel;
        this.historyViewModel = historyViewModel;
        this.historyController = historyController;
        accountInfoViewModel.addPropertyChangeListener(this);
        historyViewModel.addPropertyChangeListener(this);

        AccountInfoState state = accountInfoViewModel.getAccountInfoState();

        JLabel fundsLabel = new GreenCustomJLabel(accountInfoViewModel.FUNDS_LABEL + state.getFunds());
        JLabel userLabel = new GreenCustomJLabel(accountInfoViewModel.USER_LABEL + state.getUsername());
        fundsPanel = new GreenCustomPanel();
        fundsPanel.add(fundsLabel);

        userPanel = new GreenCustomPanel();
        userPanel.add(userLabel);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new FundsFieldFormatter(format);
        depositField = new FundsField(formatter);
        withdrawField = new FundsField(formatter);

        depositField.getDocument().addDocumentListener(this);
        withdrawField.getDocument().addDocumentListener(this);

        deposit = new GreenCustomButton(accountInfoViewModel.DEPOSIT_LABEL);
        withdraw = new GreenCustomButton(accountInfoViewModel.WITHDRAW_LABEL);
        signout = new GreenCustomButton(accountInfoViewModel.SIGN_OUT_LABEL);
        exit = new GreenCustomButton(accountInfoViewModel.EXIT_LABEL);

        JPanel depositPanel = new GreenCustomPanel();
        depositPanel.add(depositField);
        depositPanel.add(deposit);

        JPanel withdrawPanel = new GreenCustomPanel();
        withdrawPanel.add(withdrawField);
        withdrawPanel.add(withdraw);


        JPanel statsPanel = new GreenCustomPanel();
        statsPanel.add(new GreenCustomJLabel(accountInfoViewModel.STATISTICS_LABEL));

        // todo change to constants from the view model
        blackJackButton = new GreenCustomButton("blackjack");
        baccararatButton = new GreenCustomButton("baccarat");
        warButton = new GreenCustomButton("war");

        statsPanel.add(blackJackButton);
        statsPanel.add(baccararatButton);
        statsPanel.add(warButton);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        signout.addActionListener(this);
        exit.addActionListener(this);
        blackJackButton.addActionListener(this);
        baccararatButton.addActionListener(this);
        warButton.addActionListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        gridBagUtils.addComponentWithConstraints(statsPanel, 2, 0, 4, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(userPanel, 0, 0, 2, 2, 1, 1);
        gridBagUtils.addComponentWithConstraints(fundsPanel, 0, 2, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(depositPanel, 2, 2, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(withdrawPanel, 4, 2, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(exit, 0, 3, 3, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(signout, 3, 3, 3, 1, 1, 1);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        if ((evt.getSource().equals(deposit) && currState.getChange() > 0) || (evt.getSource().equals(withdraw) && currState.getChange() < 0)) {
            updateController.updateUser(currState.getUsername(), currState.getChange());
        } else if (evt.getSource().equals(signout)) {
            signOutController.execute();
        } else if (evt.getSource().equals(exit)) {
            exitController.execute();
        } else if (evt.getSource().equals(baccararatButton) ||
                evt.getSource().equals(blackJackButton) ||
                evt.getSource().equals(warButton)) {
            historyController.execute(currState.getUsername(), evt.getActionCommand());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        if (evt.getPropertyName().equals("history")) {
            // Update the view with the new chart data
            HistoryState state = historyViewModel.getHistoryState();
            XYChart chart = state.getChart();
            if (chart != null) {
                displayChart(chart);
            }
        } else if (evt.getPropertyName().equals("account info") && currState.getErrorMessage() == null) {
            fundsLabel = new GreenCustomJLabel(accountInfoViewModel.FUNDS_LABEL + currState.getFunds());
            userLabel = new GreenCustomJLabel(accountInfoViewModel.USER_LABEL + currState.getUsername());

            fundsPanel.removeAll();
            userPanel.removeAll();
            fundsPanel.add(fundsLabel);
            userPanel.add(userLabel);
            revalidate();
            repaint();
            if (Math.abs(currState.getChange()) > 0) {
                JOptionPane.showMessageDialog(this, accountInfoViewModel.SUCCESS_NOTE);
            }
        } else if (evt.getPropertyName().equals("account info")) {
            JOptionPane.showMessageDialog(this, currState.getErrorMessage());
        }
        withdrawField.setValue(0);
        depositField.setValue(0);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // todo fix bug
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        if (e.getDocument() == depositField.getDocument()) {
            currState.setChange(Integer.parseInt(depositField.getText()));
        } else if (e.getDocument() == withdrawField.getDocument()) {
            currState.setChange(-Integer.parseInt(withdrawField.getText()));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {}

    @Override
    public void changedUpdate(DocumentEvent e) {}

    private void displayChart(XYChart chart) {
        // Create a new frame for the chart popup
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
}