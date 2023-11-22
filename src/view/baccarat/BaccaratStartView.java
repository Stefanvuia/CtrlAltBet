package view.baccarat;

import interface_adapter.baccarat.BaccaratController;
import interface_adapter.baccarat.BaccaratStartState;
import interface_adapter.baccarat.BaccaratStartViewModel;
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
import java.io.IOException;
import java.text.NumberFormat;

public class BaccaratStartView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "baccarat start";
    private final BaccaratStartViewModel baccaratStartViewModel;
    private final BaccaratController baccaratController;

    JFormattedTextField bankerBet;

    JFormattedTextField playerBet;

    JFormattedTextField tieBet;

    final JButton start;

    final JButton info;

    final JButton exit;

    final JPanel fundPanel;

    public BaccaratStartView(BaccaratStartViewModel baccaratStartViewModel, BaccaratController baccaratController) throws IOException {
        this.baccaratStartViewModel = baccaratStartViewModel;
        this.baccaratController = baccaratController;
        this.baccaratStartViewModel.addPropertyChangeListener(this);

        start = new GreenCustomButton(baccaratStartViewModel.START_LABEL);
        info = new GreenCustomButton(baccaratStartViewModel.INFO_LABEL);
        exit = new GreenCustomButton(baccaratStartViewModel.EXIT_LABEL);

        makeBetFields();

        start.addActionListener(e -> {
            if (e.getSource().equals(start)) {
                BaccaratStartState currState = baccaratStartViewModel.getState();

                System.out.println(currState.getBet());
                baccaratController.execute(currState.getBet(), currState.getUsername());
            }
        });

        bankerBet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BaccaratStartState currentState = baccaratStartViewModel.getState();
                currentState.setBet("banker", Integer.parseInt(bankerBet.getText()));
                baccaratStartViewModel.setState(currentState);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        tieBet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BaccaratStartState currentState = baccaratStartViewModel.getState();
                currentState.setBet("tie", Integer.parseInt(tieBet.getText()));
                baccaratStartViewModel.setState(currentState);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        playerBet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println(playerBet.getText());
                BaccaratStartState currentState = baccaratStartViewModel.getState();
                currentState.setBet("player", Integer.parseInt(playerBet.getText()));
                baccaratStartViewModel.setState(currentState);
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
        this.add(new BlackJackBackgroundPanel(baccaratStartViewModel.IMG_PATH), gbc);
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
        makeFundsLabel();
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
        BaccaratStartState currState = baccaratStartViewModel.getState();
        if (!(currState.getErrorMessage().isEmpty())) {
            JOptionPane.showMessageDialog(this, currState.getErrorMessage());
        }
        makeFundsLabel();

        bankerBet.setValue(0);
        tieBet.setValue(0);
        playerBet.setValue(0);
    }

    private void makeBetFields() {
        int max = baccaratStartViewModel.getState().getFund();

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new BetFieldFormatter(format, max);

        bankerBet = new BetField(formatter);
        tieBet = new BetField(formatter);
        playerBet = new BetField(formatter);
    }

    private void makeFundsLabel() {
        fundPanel.removeAll();
        JLabel newFundLabel = new GreenCustomJLabel("available: " + baccaratStartViewModel.getState().getFund());
        fundPanel.add(newFundLabel);
    }
}
