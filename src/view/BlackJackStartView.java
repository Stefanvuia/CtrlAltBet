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
    public final String viewName = "abc";

    private final BlackJackStartViewModel blackJackStartViewModel;

    final JFormattedTextField betField = new JFormattedTextField(setUpNumFormat());

    final JButton start;

    private final BlackJackStartController blackJackStartController;

    public BlackJackStartView(BlackJackStartViewModel blackJackStartViewModel,
                              BlackJackStartController blackJackStartController) {
        this.blackJackStartViewModel = blackJackStartViewModel;
        this.blackJackStartController = blackJackStartController;
        this.blackJackStartViewModel.addPropertyChangeListener(this);

        betField.setColumns(10);

        LabelTextPanel betInfo = new LabelTextPanel(
                new JLabel("Bet"), betField);

        JLabel title = new JLabel(blackJackStartViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        start = new JButton(blackJackStartViewModel.BET_LABEL);
        buttons.add(start);

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(betInfo);
        this.add(buttons);
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
