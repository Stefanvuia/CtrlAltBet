package view.blackjack;

import interface_adapter.blackjack.blackjack_logic.*;
import interface_adapter.menu.exit.ExitController;
import tools.GridBagUtils;
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

        exit.addActionListener(this);
        hit.addActionListener(this);
        stand.addActionListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        // exit button
        gridBagUtils.addComponentWithConstraints(exit, 0, 7, 2, 1, 0.25, 0.05);

        // hit button
        gridBagUtils.addComponentWithConstraints(hit, 2, 7, 3, 1, 0.5, 0.05);

        // stand button
        gridBagUtils.addComponentWithConstraints(stand, 5, 7, 3, 1, 0.5, 0.05);

        // table
        this.tablePanel = new BlackJackBackgroundPanel(blackJackIngameViewModel.IMG_PATH);
        gridBagUtils.addComponentWithConstraints(tablePanel, 0, 0, 8, 7, 1, 1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BlackJackGameState currstate = blackJackIngameViewModel.getState();
        Object source = e.getSource();

        if (source.equals(exit)) {
            exitController.execute();
        } else if (source.equals(hit)) {
            blackJackHitController.execute(currstate.getGame());
        } else if (source.equals(stand)) {
            blackJackStandController.execute(currstate.getGame());
        }
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