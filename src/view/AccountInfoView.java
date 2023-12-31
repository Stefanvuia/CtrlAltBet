package view;

import interface_adapter.account_menu.AccountInfoState;
import interface_adapter.account_menu.AccountInfoViewModel;
import interface_adapter.account_menu.history.HistoryController;
import interface_adapter.account_menu.history.HistoryState;
import interface_adapter.account_menu.history.HistoryViewModel;
import interface_adapter.account_menu.reset_graph.ResetController;
import interface_adapter.account_menu.sign_out.SignOutController;
import interface_adapter.account_menu.update.UserUpdateController;
import interface_adapter.game_menu.exit.ExitController;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import tools.GridBagUtils;
import view.custom_swing_elements.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.NumberFormat;

/**
 * Represents the account information view in a user interface, displaying details such as the current user's
 * username and balance. This view allows for actions such as depositing and withdrawing funds, signing out,
 * and exiting the application. It also provides functionality for viewing payout statistics
 * for blackjack, baccarat, and war in a graph and resetting all graphs.
 *
 * <p>This class extends {@link JPanel} and implements {@link ActionListener}, {@link PropertyChangeListener},
 * and {@link DocumentListener} to handle user interactions and respond to changes in the model.
 *
 * @see javax.swing.JPanel
 * @see java.awt.event.ActionListener
 * @see java.beans.PropertyChangeListener
 * @see javax.swing.event.DocumentListener
 */
public class AccountInfoView extends JPanel implements ActionListener, PropertyChangeListener, DocumentListener {
    private final AccountInfoViewModel accountInfoViewModel;
    private final HistoryController historyController;
    private final HistoryViewModel historyViewModel;
    private final ExitController exitController;
    private final UserUpdateController updateController;
    private final SignOutController signOutController;
    private final ResetController resetController;
    private final JButton deposit;
    private final JButton withdraw;
    private final JButton signout;
    private final JButton exit;
    private final JFormattedTextField depositField;
    private final JFormattedTextField withdrawField;
    private final JPanel fundsPanel;
    private final JPanel userPanel;
    private final JButton blackJackButton;
    private final JButton baccaratButton;
    private final JButton warButton;
    private final JButton resetGraph;
    public String viewName = "account info";
    private JLabel fundsLabel;
    private JLabel userLabel;

    /**
     * Constructs an AccountInfoView with the given models and controllers.
     *
     * @param accountInfoViewModel The view model for account information.
     * @param exitController       The controller to handle exit action.
     * @param updateController     The controller for updating the user's balance information.
     * @param signOutController    The controller for sign-out functionality.
     * @param historyController    The controller for handling betting history graph view actions.
     * @param historyViewModel     The view model for betting history information.
     * @param resetController      The controller for resetting all game statistics graph.
     */
    public AccountInfoView(AccountInfoViewModel accountInfoViewModel,
                           ExitController exitController,
                           UserUpdateController updateController,
                           SignOutController signOutController,
                           HistoryController historyController,
                           HistoryViewModel historyViewModel,
                           ResetController resetController) {
        this.exitController = exitController;
        this.updateController = updateController;
        this.signOutController = signOutController;
        this.accountInfoViewModel = accountInfoViewModel;
        this.historyViewModel = historyViewModel;
        this.historyController = historyController;
        this.resetController = resetController;
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
        exit = new GreenCustomButton(accountInfoViewModel.BACK_LABEL);

        JPanel depositPanel = new GreenCustomPanel();
        depositPanel.add(depositField);
        depositPanel.add(deposit);

        JPanel withdrawPanel = new GreenCustomPanel();
        withdrawPanel.add(withdrawField);
        withdrawPanel.add(withdraw);

        JPanel statsPanel = new GreenCustomPanel();
        statsPanel.add(new GreenCustomJLabel(accountInfoViewModel.STATISTICS_LABEL));

        blackJackButton = new GreenCustomButton(HistoryViewModel.BLACKJACK_BUTTON_LABEL);
        baccaratButton = new GreenCustomButton(HistoryViewModel.BACCARAT_BUTTON_LABEL);
        warButton = new GreenCustomButton(HistoryViewModel.WAR_BUTTON_LABEl);
        resetGraph = new GreenCustomButton(HistoryViewModel.RESET_GRAPH_LABEL);

        statsPanel.add(blackJackButton);
        statsPanel.add(baccaratButton);
        statsPanel.add(warButton);

        // Positioning Reset Graph button
        GridBagConstraints gbc = new GridBagConstraints();

        Component filler = Box.createVerticalStrut(10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        statsPanel.add(filler, gbc);

        gbc.gridx = 1;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        statsPanel.add(resetGraph, gbc);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        signout.addActionListener(this);
        exit.addActionListener(this);
        blackJackButton.addActionListener(this);
        baccaratButton.addActionListener(this);
        warButton.addActionListener(this);
        resetGraph.addActionListener(this);

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
     * Handles action events triggered by user interaction, such as button clicks.
     * This method responds to various actions like deposit, withdrawal, sign-out, exit,
     * viewing game statistics, and resetting those statistics.
     *
     * @param evt The action event triggered by the user.
     */
    public void actionPerformed(ActionEvent evt) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        if ((evt.getSource().equals(deposit) && currState.getDeposit() > 0)) {
            updateController.updateUser(currState.getUsername(), currState.getDeposit());
        } else if ((evt.getSource().equals(withdraw) && currState.getWithdraw() > 0)) {
            updateController.updateUser(currState.getUsername(), -currState.getWithdraw());
        } else if (evt.getSource().equals(signout)) {
            signOutController.execute();
        } else if (evt.getSource().equals(exit)) {
            exitController.execute();
        } else if (evt.getSource().equals(baccaratButton) ||
                evt.getSource().equals(blackJackButton) ||
                evt.getSource().equals(warButton)) {
            historyController.execute(currState.getUsername(), evt.getActionCommand());
        } else if (evt.getSource().equals(resetGraph)) {
            // Prompt the user to confirm the reset action
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to reset all your graph history?",
                    "Confirm Reset",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    resetController.execute(currState.getUsername());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Responds to property change events. This method updates the view based on changes in the
     * account information or history states, such as updating the balance or displaying an error message.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        if (evt.getPropertyName().equals("history")) {
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
            if (currState.getWithdraw() > 0 || currState.getDeposit() > 0) {
                JOptionPane.showMessageDialog(this, accountInfoViewModel.SUCCESS_NOTE);
            }
        } else if (evt.getPropertyName().equals("account info")) {
            JOptionPane.showMessageDialog(this, currState.getErrorMessage());
        }
        withdrawField.setValue(0);
        depositField.setValue(0);
    }

    /**
     * Handles insertions in document fields. This method updates the state based on user input in
     * fields like deposit and withdrawal amounts.
     *
     * @param e The document event indicating an insertion.
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        AccountInfoState currState = accountInfoViewModel.getAccountInfoState();
        if (e.getDocument() == depositField.getDocument()) {
            currState.setDeposit(Integer.parseInt(depositField.getText()));
        } else if (e.getDocument() == withdrawField.getDocument()) {
            currState.setWithdraw(Integer.parseInt(withdrawField.getText()));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

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