package view.baccarat;

import interface_adapter.baccarat.BaccaratController;
import interface_adapter.baccarat.BaccaratState;
import interface_adapter.baccarat.BaccaratViewModel;
import interface_adapter.blackjack.blackjack_start.BlackJackStartState;
import view.custom_swing_elements.BetField;
import view.custom_swing_elements.BlackJackBackgroundPanel;
import view.custom_swing_elements.GreenCustomButton;
import view.custom_swing_elements.GreenCustomPanel;

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

public class BaccaratStartView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "baccarat start";
    private final BaccaratViewModel baccaratViewModel;
    private final BaccaratController baccaratController;

    final JFormattedTextField bankerBet;

    final JFormattedTextField playerBet;

    final JFormattedTextField tieBet;

    final JButton start;

    final JButton info;

    final JButton exit;

    final JPanel fundPanel;

    public BaccaratStartView(BaccaratViewModel baccaratViewModel, BaccaratController baccaratController) throws IOException {
        this.baccaratViewModel = baccaratViewModel;
        this.baccaratController = baccaratController;
        this.baccaratViewModel.addPropertyChangeListener(this);

        start = new GreenCustomButton(baccaratViewModel.START_LABEL);
        info = new GreenCustomButton(baccaratViewModel.INFO_LABEL);
        exit = new GreenCustomButton(baccaratViewModel.EXIT_LABEL);

        int max = baccaratViewModel.getState().getFund();

        bankerBet = setUpNumFormat(max);
        tieBet = setUpNumFormat(max);
        playerBet = setUpNumFormat(max);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(start)) {
                    BaccaratState currState = baccaratViewModel.getState();

                    System.out.println(currState.getBet());
                    baccaratController.execute(currState.getBet(), currState.getUsername());
                }
            }
        });

        bankerBet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BaccaratState currentState = baccaratViewModel.getState();
                currentState.setBet("banker", Integer.parseInt(bankerBet.getText()));
                baccaratViewModel.setState(currentState);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        tieBet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BaccaratState currentState = baccaratViewModel.getState();
                currentState.setBet("tie", Integer.parseInt(tieBet.getText()));
                baccaratViewModel.setState(currentState);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        playerBet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BaccaratState currentState = baccaratViewModel.getState();
                currentState.setBet("player", Integer.parseInt(playerBet.getText()));
                baccaratViewModel.setState(currentState);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // table
        gbc.gridwidth = 8;
        gbc.gridheight = 6;
        this.add(new BlackJackBackgroundPanel(baccaratViewModel.IMG_PATH), gbc);
        gbc.weightx = 0.25;
        gbc.weighty = 0;

        // exit button
        gbc.gridy = 6;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        JPanel exitPanel = new JPanel(new BorderLayout(0, 0));
        exitPanel.add(exit);
        this.add(exitPanel, gbc);

        // info button
        gbc.gridy = 6;
        gbc.gridx = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.add(info);
        this.add(infoPanel, gbc);
        gbc.weightx = 0.5;
        gbc.weighty = 0;

        // betting fields
        gbc.gridx = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        JPanel betPanel = new GreenCustomPanel();
        betPanel.add(playerBet);
        betPanel.add(tieBet);
        betPanel.add(bankerBet);
        this.add(betPanel, gbc);

        // current funds
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        fundPanel = new GreenCustomPanel();
        JLabel fundLabel = new JLabel("available: " + max);
        fundLabel.setFont(new Font("Courier", Font.BOLD, 26));
        fundLabel.setForeground(new Color(144, 227, 154));
        fundPanel.add(fundLabel);
        this.add(fundPanel, gbc);

        // start button
        gbc.gridx = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        JPanel startPanel = new JPanel(new BorderLayout(0, 0));
        startPanel.add(start);
        this.add(startPanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BaccaratState currState = baccaratViewModel.getState();
        if(currState.getPlayerHandImg().isEmpty()) {
            JOptionPane.showMessageDialog(this, currState.getGameMessage());
        }
        fundPanel.removeAll();
        JLabel newFundLabel = new JLabel("available: " + currState.getFund());
        newFundLabel.setFont(new Font("Courier", Font.BOLD, 26));
        newFundLabel.setForeground(new Color(144, 227, 154));
        fundPanel.add(newFundLabel);
        bankerBet.setValue(0);
        tieBet.setValue(0);
        playerBet.setValue(0);
    }

    private JFormattedTextField setUpNumFormat(int max) {
        NumberFormat betFormat = NumberFormat.getIntegerInstance();
        betFormat.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(betFormat);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(max);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField field = new BetField(formatter);
        field.setValue(0);
        field.setColumns(10);

        return field;
    }
}
