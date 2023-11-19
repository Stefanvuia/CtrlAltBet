package view;

import interface_adapter.blackjack.blackjack_start.BlackJackStartController;
import interface_adapter.blackjack.blackjack_start.BlackJackStartViewModel;
import interface_adapter.blackjack.blackjack_start.StartState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.*;


public class BlackJackStartView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "bj start";

    private final BlackJackStartViewModel blackJackStartViewModel;

    final JFormattedTextField betField = new JFormattedTextField(setUpNumFormat());

    final JButton start;

    final JButton info;

    final JButton exit;

    private final BlackJackStartController blackJackStartController;

    public BlackJackStartView(BlackJackStartViewModel blackJackStartViewModel,
                              BlackJackStartController blackJackStartController) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackStartController = blackJackStartController;
        this.blackJackStartViewModel.addPropertyChangeListener(this);

        betField.setColumns(10);

        LabelTextPanel betInfo = new LabelTextPanel(
                new JLabel("Bet"), betField);

        start = new CustomButton(blackJackStartViewModel.BET_LABEL);
        info = new CustomButton(blackJackStartViewModel.INFO_LABEL);
        exit = new CustomButton(blackJackStartViewModel.EXIT_LABEL);

        start.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(start)) {
                            StartState currentState = blackJackStartViewModel.getState();

                            System.out.println(currentState.getUsername());
                            System.out.println(currentState.getBet());

//                            blackJackStartController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getBet());
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

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 0.1;
        gbc.weightx = 0.25;
        JPanel exitPanel = new JPanel(new BorderLayout(0, 0));
        exitPanel.add(exit);
        this.add(exitPanel, gbc);

        gbc.gridx++;
        gbc.weightx = 1.0;
        JPanel betPanel = new CustomPanel();
        betPanel.add(betField);
        this.add(betPanel, gbc);

        gbc.gridx++;
        gbc.weightx = 0.25;
        JPanel startPanel = new JPanel(new BorderLayout(0, 0));
        startPanel.add(start);
        this.add(startPanel, gbc);

        gbc.gridx++;
        gbc.weightx = 0.25;
        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.add(info);
        this.add(infoPanel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0.9;
        gbc.gridwidth = gbc.REMAINDER;
        this.add(new CustomPanel(), gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StartState currentState = blackJackStartViewModel.getState();
        JOptionPane.showMessageDialog(this, currentState.getBetError());
    }
    private NumberFormatter setUpNumFormat() {
        NumberFormat betFormat = NumberFormat.getIntegerInstance();
        betFormat.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(betFormat);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        return formatter;
    }
}
