package view.war;

import interface_adapter.game_menu.exit.ExitController;
import interface_adapter.war.war_start.WarStartController;
import interface_adapter.war.war_start.WarStartState;
import interface_adapter.war.war_start.WarStartViewModel;
import tools.GridBagUtils;
import view.custom_swing_elements.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class WarStartView extends JPanel implements ActionListener, PropertyChangeListener, DocumentListener {
    public final String viewName = "war start";
    private final WarStartViewModel warStartViewModel;
    private final ExitController exitController;
    JFormattedTextField betField;
    final JButton start;
    final JButton info;
    final JButton exit;
    final JButton max;

    final JButton min;

    final JButton half;

    private int maxBet;

    private int halfBet;
    private final WarStartController warStartController;

    public WarStartView(WarStartViewModel warStartViewModel,
                        ExitController exitController,
                        WarStartController warStartController) throws IOException {
        this.warStartViewModel = warStartViewModel;
        this.exitController = exitController;
        this.warStartController = warStartController;
        this.warStartViewModel.addPropertyChangeListener(this);

        halfBet = warStartViewModel.getState().getFunds() / 2;
        maxBet = warStartViewModel.getState().getFunds();

        makeBetFields();

        start = new GreenCustomButton(warStartViewModel.BET_LABEL);
        info = new GreenCustomButton(warStartViewModel.INFO_LABEL);
        exit = new RedCustomButton(warStartViewModel.EXIT_LABEL);
        max = new GreenCustomButton(warStartViewModel.MAX_BET_LABEL + maxBet);
        min = new GreenCustomButton(warStartViewModel.MIN_BET_LABEL);
        half = new GreenCustomButton(warStartViewModel.HALF_BET_LABEL + halfBet);

        start.addActionListener(this);
        info.addActionListener(this);
        exit.addActionListener(this);
        max.addActionListener(this);
        min.addActionListener(this);
        half.addActionListener(this);
        betField.getDocument().addDocumentListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        // exit button
        gridBagUtils.addComponentWithConstraints(exit, 0, 6, 2, 2, 1, 0);

        // info button
        gridBagUtils.addComponentWithConstraints(info, 6, 6, 2, 2, 1, 0);

        // betting fields
        GreenCustomPanel betPanel = new GreenCustomPanel();
        betPanel.add(betField);
        betPanel.add(min);
        betPanel.add(half);
        betPanel.add(max);
        gridBagUtils.addComponentWithConstraints(betPanel, 2, 6, 2, 2, 1, 0);

        // start button
        gridBagUtils.addComponentWithConstraints(start, 4, 6, 2, 2, 1, 0);

        // table
        JPanel tablePanel = new BlackJackBackgroundPanel(warStartViewModel.IMG_PATH);
        gridBagUtils.addComponentWithConstraints(tablePanel, 0, 0, 8, 6, 1, 1);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        WarStartState currentState = warStartViewModel.getState();

        Object source = e.getSource();

        if (source.equals(start)) {
            warStartController.execute(currentState.getUsername(), currentState.getBet());
        } else if (source.equals(info)) {
            openGameInfo();
        } else if (source.equals(exit)) {
            exitController.execute();
        } else if (source.equals(half)) {
            updateBetAndField(halfBet);
        } else if (source.equals(max)) {
            updateBetAndField(maxBet);
        } else if (source.equals(min)) {
            updateBetAndField(0);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("war start")) {
            WarStartState currentState = warStartViewModel.getState();
            if (currentState.getBetError() != null) {
                JOptionPane.showMessageDialog(this, currentState.getBetError());
            }
            halfBet = currentState.getFunds() / 2;
            maxBet = currentState.getFunds();
            half.setText(warStartViewModel.HALF_BET_LABEL + halfBet);
            max.setText(warStartViewModel.MAX_BET_LABEL + maxBet);
            betField.setValue(0);
        }
    }
    private void makeBetFields() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new FundsFieldFormatter(format);

        betField = new FundsField(formatter);
    }

    private void openGameInfo() {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(warStartViewModel.INFO_PATH));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "See game info here: " + warStartViewModel.INFO_PATH);
        }
    }

    private void updateBetAndField(int newBet) {
        WarStartState currentState = warStartViewModel.getState();
        currentState.setBet(newBet);
        warStartViewModel.setState(currentState);
        betField.setValue(newBet);
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        WarStartState currentState = warStartViewModel.getState();
        currentState.setBet(Integer.parseInt(betField.getText()));
        warStartViewModel.setState(currentState);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {}

    @Override
    public void changedUpdate(DocumentEvent e) {}
}
