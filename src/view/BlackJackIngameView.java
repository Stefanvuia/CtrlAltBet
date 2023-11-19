package view;

import interface_adapter.blackjack.blackjack_logic.*;
import view.custom_swing_elements.BackgroundPanel;
import view.custom_swing_elements.GreenCustomButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
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

    private BackgroundPanel tablePanel;

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
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(hit)) {
                            GameState currstate = blackJackHitViewModel.getState();
                            blackJackHitController.execute(currstate.getGame());
                        }
                    }
                }
        );

        stand.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(stand)) {
                            GameState currstate = blackJackStandViewModel.getState();
                            blackJackStandController.execute(currstate.getGame());
                        }
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
        gbc.gridwidth = gbc.REMAINDER;
        this.tablePanel = new BackgroundPanel("img/blackjacktable.png");
        this.add(tablePanel, gbc);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("bj hit")) {
            GameState currState = blackJackHitViewModel.getState();
            try {
                showPlayerHand(currState.getPlayerHandImg());
                showDealerHand(currState.getDealerHandImg(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (currState.isGameEnd()) {
                JOptionPane.showMessageDialog(this, currState.getGameMessage());
            }
        } else if (evt.getPropertyName().equals("bj stand")) {
            GameState currState = blackJackStandViewModel.getState();
            try {
                showDealerHand(currState.getDealerHandImg(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (currState.isGameEnd()) {
                JOptionPane.showMessageDialog(this, currState.getGameMessage());
            }
        }
    }

    private void showPlayerHand(List<String> imgs) throws IOException {
        tablePanel.getBottom().removeAll();
        for (String img : imgs) {
            URL url = new URL(img);
            Image image = ImageIO.read(url).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(image));
            tablePanel.getBottom().add(label);
        }
        revalidate();
        repaint();
    }

    private void showDealerHand(List<String> imgs, boolean showSecondCard) throws IOException {
        tablePanel.getTop().removeAll();
        if (showSecondCard) {
            for (String img : imgs) {
                URL url = new URL(img);
                Image image = ImageIO.read(url).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(image));
                tablePanel.getTop().add(label);
            }
        } else {
            URL url = new URL(imgs.get(0));
            Image image = ImageIO.read(url).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(image));
            tablePanel.getTop().add(label);

            URL cardBackUrl = new URL(blackJackStandViewModel.CARD_BACK_URL);
            Image cardBackImg = ImageIO.read(cardBackUrl).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            JLabel cardBack = new JLabel();
            cardBack.setIcon(new ImageIcon(cardBackImg));
            tablePanel.getTop().add(cardBack);
        }
        revalidate();
        repaint();
    }
}
