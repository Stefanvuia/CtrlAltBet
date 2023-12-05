package view.baccarat;

import interface_adapter.baccarat.BaccaratController;
import interface_adapter.baccarat.BaccaratStartState;
import interface_adapter.baccarat.BaccaratStartViewModel;
import interface_adapter.game_menu.exit.ExitController;
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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;

public class BaccaratStartView extends JPanel implements ActionListener, PropertyChangeListener, DocumentListener {
    public final String viewName = "baccarat start";
    private final BaccaratStartViewModel baccaratStartViewModel;
    private final BaccaratController baccaratController;

    private final ExitController exitController;

    JFormattedTextField bankerBet;

    JFormattedTextField playerBet;

    JFormattedTextField tieBet;

    final JButton start;

    final JButton info;

    final JButton exit;

    final JPanel fundPanel;

    public BaccaratStartView(BaccaratStartViewModel baccaratStartViewModel,
                             BaccaratController baccaratController,
                             ExitController exitController) throws IOException {
        this.baccaratStartViewModel = baccaratStartViewModel;
        this.exitController = exitController;
        this.baccaratController = baccaratController;
        this.baccaratStartViewModel.addPropertyChangeListener(this);

        start = new GreenCustomButton(baccaratStartViewModel.START_LABEL);
        info = new GreenCustomButton(baccaratStartViewModel.INFO_LABEL);
        exit = new RedCustomButton(baccaratStartViewModel.EXIT_LABEL);

        makeBetFields();

        exit.addActionListener(this);
        start.addActionListener(this);
        info.addActionListener(this);

        bankerBet.getDocument().addDocumentListener(this);
        tieBet.getDocument().addDocumentListener(this);
        playerBet.getDocument().addDocumentListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        // table
        gridBagUtils.addComponentWithConstraints(new BlackJackBackgroundPanel(baccaratStartViewModel.IMG_PATH), 0, 0, 8, 6, 1, 1);

        // exit button
        gridBagUtils.addComponentWithConstraints(exit, 0, 6, 2, 2, 0.25, 0);

        // info button
        gridBagUtils.addComponentWithConstraints(info, 6, 6, 2, 2, 0.25, 0);

        // betting fields
        JPanel betPanel = new GreenCustomPanel();
        betPanel.add(playerBet);
        betPanel.add(tieBet);
        betPanel.add(bankerBet);
        gridBagUtils.addComponentWithConstraints(betPanel, 2, 6, 4, 1, 0.5, 0);

        // current funds
        fundPanel = new GreenCustomPanel();
        makeFundsLabel();
        gridBagUtils.addComponentWithConstraints(fundPanel, 2, 7, 2, 1, 0.25, 0);

        // start button
        gridBagUtils.addComponentWithConstraints(start, 4, 7, 2, 1, 0.25, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exit)) {
            exitController.execute();
        } else if (e.getSource().equals(start)) {
            BaccaratStartState currState = baccaratStartViewModel.getState();
            baccaratController.execute(currState.getBet(), currState.getUsername());
        } else if (e.getSource().equals(info)) {
            openGameInfo(baccaratStartViewModel.INFO_PATH);
        }
    }

    private void openGameInfo(String infoPath) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(infoPath));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "See game info here: " + infoPath);
        }
    }

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
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new FundsFieldFormatter(format);

        bankerBet = new FundsField(formatter);
        tieBet = new FundsField(formatter);
        playerBet = new FundsField(formatter);
    }

    private void makeFundsLabel() {
        fundPanel.removeAll();
        JLabel newFundLabel = new GreenCustomJLabel("available: " + baccaratStartViewModel.getState().getFund());
        fundPanel.add(newFundLabel);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == bankerBet.getDocument()) {
            updateBet(bankerBet, baccaratStartViewModel.BANKER_BET);
        } else if (e.getDocument() == tieBet.getDocument()) {
            updateBet(tieBet, baccaratStartViewModel.TIE_BET);
        } else if (e.getDocument() == playerBet.getDocument()) {
            updateBet(playerBet, baccaratStartViewModel.PLAYER_BET);
        }
    }

    private void updateBet(JTextField textField, String betType) {
        BaccaratStartState currentState = baccaratStartViewModel.getState();
        currentState.setBet(betType, Integer.parseInt(textField.getText()));
        baccaratStartViewModel.setState(currentState);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {}

    @Override
    public void changedUpdate(DocumentEvent e) {}
}
