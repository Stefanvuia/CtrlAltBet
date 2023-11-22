package view.blackjack;

import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.menu.exit.ExitController;
import view.custom_elements.BlackJackBackgroundPanel;
import view.custom_elements.GreenCustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class BlackJackIngameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "bj ingame";

    final JButton exit;

    final JButton hit;

    final JButton stand;

    private final BlackJackIngameViewModel blackJackIngameViewModel;

    private final BlackJackStandController blackJackStandController;

    private final BlackJackHitController blackJackHitController;

    private final ExitController exitController;

    private final BlackJackBackgroundPanel tablePanel;

    public BlackJackIngameView(BlackJackHitController blackJackHitController,
                               BlackJackStandController blackJackStandController,
                               ExitController exitController,
                               BlackJackIngameViewModel blackJackIngameViewModel) throws IOException {
        this.blackJackHitController = blackJackHitController;
        this.blackJackStandController = blackJackStandController;
        this.exitController = exitController;
        this.blackJackIngameViewModel = blackJackIngameViewModel;
        this.blackJackIngameViewModel.addPropertyChangeListener(this);

        exit = new GreenCustomButton(blackJackIngameViewModel.EXIT_LABEL);
        hit = new GreenCustomButton(BlackJackIngameView.this.blackJackIngameViewModel.HIT_LABEL);
        stand = new GreenCustomButton(blackJackIngameViewModel.STAND_LABEL);

        exit.addActionListener(
                e -> {
                    if (e.getSource().equals(exit)) {
                        exitController.execute();
                    }
                }
        );

        hit.addActionListener(
                e -> {
                    if (e.getSource().equals(hit)) {
                        BlackJackGameState currstate = BlackJackIngameView.this.blackJackIngameViewModel.getState();
                        blackJackHitController.execute(currstate.getGame());
                    }
                }
        );

        stand.addActionListener(
                e -> {
                    if (e.getSource().equals(stand)) {
                        BlackJackGameState currstate = blackJackIngameViewModel.getState();
                        blackJackStandController.execute(currstate.getGame());
                    }
                }
        );

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

        // hit button
        gbc.gridx++;
        gbc.weightx = 0.75;
        JPanel hitPanel = new JPanel(new BorderLayout(0, 0));
        hitPanel.add(hit);
        this.add(hitPanel, gbc);

        // stand button
        gbc.gridx++;
        gbc.weightx = 0.75;
        JPanel standPanel = new JPanel(new BorderLayout(0, 0));
        standPanel.add(stand);
        this.add(standPanel, gbc);

        // table
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0.9;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.tablePanel = new BlackJackBackgroundPanel(blackJackIngameViewModel.IMG_PATH);
        this.add(tablePanel, gbc);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BlackJackGameState currState = blackJackIngameViewModel.getState();
        showPlayerHand(currState.getPlayerImages());
        showDealerHand(currState.getDealerImages());
        if (currState.isGameEnd()) {
            JOptionPane.showMessageDialog(this, currState.getGameMessage());
        }
    }

    private void showPlayerHand(List<Image> imgs) {
        tablePanel.getBottom().removeAll();
        for (Image img : imgs) {
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(img));
            tablePanel.getBottom().add(label);
        }
        revalidate();
        repaint();
    }

    private void showDealerHand(List<Image> imgs) {
        tablePanel.getTop().removeAll();
        for (Image img : imgs) {
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(img));
            tablePanel.getTop().add(label);
        }
        revalidate();
        repaint();
    }
}