package view;

import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.blackjack.blackjack_start.StartState;
import view.custom_swing_elements.BackgroundPanel;
import view.custom_swing_elements.BetField;
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
import java.text.*;


public class BlackJackStartView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "bj start";

    private final BlackJackStartViewModel blackJackStartViewModel;

    final JFormattedTextField betField;

    final JButton start;

    final JButton info;

    final JButton exit;

    final JButton max;

    final JButton min;

    final JButton half;

    private int maxBet;

    private int halfBet;

    private final BlackJackStartController blackJackStartController;

    public BlackJackStartView(BlackJackStartViewModel blackJackStartViewModel,
                              BlackJackStartController blackJackStartController) throws IOException {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackStartController = blackJackStartController;
        this.blackJackStartViewModel.addPropertyChangeListener(this);

        halfBet = blackJackStartViewModel.getState().getFunds() / 2;
        maxBet = blackJackStartViewModel.getState().getFunds();

        betField = new BetField(setUpNumFormat(maxBet));
        betField.setColumns(5);

        start = new GreenCustomButton(blackJackStartViewModel.BET_LABEL);
        info = new GreenCustomButton(blackJackStartViewModel.INFO_LABEL);
        exit = new GreenCustomButton(blackJackStartViewModel.EXIT_LABEL);
        max = new GreenCustomButton(blackJackStartViewModel.MAX_BET_LABEL + maxBet);
        min = new GreenCustomButton(blackJackStartViewModel.MIN_BET_LABEL);
        half = new GreenCustomButton(blackJackStartViewModel.HALF_BET_LABEL + halfBet);

        start.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(start)) {
                            StartState currentState = blackJackStartViewModel.getState();

                            System.out.println(currentState.getUsername());
                            System.out.println(currentState.getBet());

                            blackJackStartController.execute(
                                    currentState.getUsername(),
                                    currentState.getBet());
                        }
                    }
                }
        );

        info.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(info)) {
                            System.out.println("info pressed");
                        }
                    }
                }
        );

        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exit)) {
                            System.out.println("exit pressed");
                        }
                    }
                }
        );

        half.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(half)) {
                            StartState currentState = blackJackStartViewModel.getState();
                            currentState.setBet(halfBet);
                            blackJackStartViewModel.setState(currentState);

                            betField.setValue(halfBet);
                        }
                    }
                }
        );

        max.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(max)) {
                            StartState currentState = blackJackStartViewModel.getState();
                            currentState.setBet(maxBet);
                            blackJackStartViewModel.setState(currentState);

                            betField.setValue(maxBet);
                        }
                    }
                }
        );

        min.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(min)) {
                            StartState currentState = blackJackStartViewModel.getState();
                            currentState.setBet(0);
                            blackJackStartViewModel.setState(currentState);

                            betField.setValue(0);
                        }
                    }
                }
        );

        betField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                StartState currentState = blackJackStartViewModel.getState();
                currentState.setBet(Integer.parseInt(betField.getText()));
                blackJackStartViewModel.setState(currentState);
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

        // exit button
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 0.1;
        gbc.weightx = 0.25;
        JPanel exitPanel = new JPanel(new BorderLayout(0, 0));
        exitPanel.add(exit);
        this.add(exitPanel, gbc);

        // betting fields
        gbc.gridx++;
        gbc.weightx = 0.75;
        GreenCustomPanel betPanel = new GreenCustomPanel();
        betPanel.add(betField);
        betPanel.add(min);
        betPanel.add(half);
        betPanel.add(max);
        this.add(betPanel, gbc);

        // start button
        gbc.gridx++;
        gbc.weightx = 0.25;
        JPanel startPanel = new JPanel(new BorderLayout(0, 0));
        startPanel.add(start);
        this.add(startPanel, gbc);

        // info button
        gbc.gridx++;
        gbc.weightx = 0.25;
        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.add(info);
        this.add(infoPanel, gbc);

        // table
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0.9;
        gbc.gridwidth = gbc.REMAINDER;
        this.add(new BackgroundPanel("img/blackjacktable.png"), gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("bj start")) {
            StartState currentState = blackJackStartViewModel.getState();
            if (currentState.getBetError() != null) {
                JOptionPane.showMessageDialog(this, currentState.getBetError());
            } else {
                halfBet = currentState.getFunds() / 2;
                maxBet = currentState.getFunds();
                half.setText(blackJackStartViewModel.HALF_BET_LABEL + halfBet);
                max.setText(blackJackStartViewModel.MAX_BET_LABEL + maxBet);
                betField.setValue(halfBet);
            }
        }
    }
    private NumberFormatter setUpNumFormat(int max) {
        NumberFormat betFormat = NumberFormat.getIntegerInstance();
        betFormat.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(betFormat);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(max);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        return formatter;
    }
}
