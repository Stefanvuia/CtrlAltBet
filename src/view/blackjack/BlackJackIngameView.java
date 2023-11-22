package view.blackjack;

import interface_adapter.blackjack.blackjack_logic.*;
import view.custom_elements.BlackJackBackgroundPanel;
import view.custom_elements.GreenCustomButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class BlackJackIngameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "bj ingame";

    final JButton exit;

    final JButton hit;

    final JButton stand;

    private final BlackJackHitViewModel blackJackHitViewModel;

    private final BlackJackStandViewModel blackJackStandViewModel;

    private final BlackJackStandController blackJackStandController;

    private final BlackJackHitController blackJackHitController;

    private final BlackJackBackgroundPanel tablePanel;

    public BlackJackIngameView(BlackJackHitController blackJackHitController,
                               BlackJackStandController blackJackStandController,
                               BlackJackHitViewModel blackJackHitViewModel,
                               BlackJackStandViewModel blackJackStandViewModel) throws IOException {
        this.blackJackHitController = blackJackHitController;
        this.blackJackStandController = blackJackStandController;
        this.blackJackHitViewModel = blackJackHitViewModel;
        this.blackJackStandViewModel = blackJackStandViewModel;
        this.blackJackHitViewModel.addPropertyChangeListener(this);
        this.blackJackStandViewModel.addPropertyChangeListener(this);

        exit = new GreenCustomButton(blackJackStandViewModel.EXIT_LABEL);
        hit = new GreenCustomButton(blackJackHitViewModel.HIT_LABEL);
        stand = new GreenCustomButton(blackJackStandViewModel.STAND_LABEL);

        hit.addActionListener(
                e -> {
                    if (e.getSource().equals(hit)) {
                        BlackJackGameState currstate = blackJackHitViewModel.getState();
                        blackJackHitController.execute(currstate.getGame());
                    }
                }
        );

        stand.addActionListener(
                e -> {
                    if (e.getSource().equals(stand)) {
                        BlackJackGameState currstate = blackJackStandViewModel.getState();
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
        this.tablePanel = new BlackJackBackgroundPanel(blackJackStandViewModel.IMG_PATH);
        this.add(tablePanel, gbc);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("bj hit")) {
            BlackJackGameState currState = blackJackHitViewModel.getState();
            showPlayerHand(currState.getPlayerImages());
            showDealerHand(currState.getDealerImages());
            if (currState.isGameEnd()) {
                JOptionPane.showMessageDialog(this, currState.getGameMessage());
            }
        } else if (evt.getPropertyName().equals("bj stand")) {
            BlackJackGameState currState = blackJackStandViewModel.getState();
            showDealerHand(currState.getDealerImages());
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