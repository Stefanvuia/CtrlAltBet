package view.war;

import interface_adapter.game_menu.exit.ExitController;
import interface_adapter.games.war.WarGameState;
import interface_adapter.games.war.war_occur.WarGoToWarController;
import interface_adapter.games.war.war_occur.WarOccurViewModel;
import interface_adapter.games.war.war_occur.WarSurrenderController;
import tools.GridBagUtils;
import view.custom_swing_elements.BlackJackBackgroundPanel;
import view.custom_swing_elements.GreenCustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class WarOccurView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "war occur";

    final JButton goToWar;

    final JButton surrender;

    final JButton exit;
    private final WarOccurViewModel warOccurViewModel;
    private final WarGoToWarController warGoToWarController;
    private final WarSurrenderController warSurrenderController;
    private final ExitController exitController;
    private final BlackJackBackgroundPanel tablePanel;


    public WarOccurView(WarOccurViewModel warOccurViewModel,
                        WarGoToWarController warGoToWarController,
                        WarSurrenderController warSurrenderController,
                        ExitController exitController) throws IOException {
        this.exitController = exitController;
        this.warGoToWarController = warGoToWarController;
        this.warSurrenderController = warSurrenderController;
        this.warOccurViewModel = warOccurViewModel;
        this.warOccurViewModel.addPropertyChangeListener(this);

        exit = new GreenCustomButton(warOccurViewModel.EXIT_LABEL);
        goToWar = new GreenCustomButton(warOccurViewModel.GO_TO_WAR_LABEL);
        surrender = new GreenCustomButton(warOccurViewModel.SURRENDER_LABEL);

        exit.addActionListener(this);
        goToWar.addActionListener(this);
        surrender.addActionListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        // exit button
        gridBagUtils.addComponentWithConstraints(exit, 0, 7, 2, 1, 0.25, 0.05);

        // hit button
        gridBagUtils.addComponentWithConstraints(goToWar, 2, 7, 3, 1, 0.5, 0.05);

        // stand button
        gridBagUtils.addComponentWithConstraints(surrender, 5, 7, 3, 1, 0.5, 0.05);

        // table
        this.tablePanel = new BlackJackBackgroundPanel(warOccurViewModel.IMG_PATH);
        gridBagUtils.addComponentWithConstraints(tablePanel, 0, 0, 8, 7, 1, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WarGameState currstate = warOccurViewModel.getState();
        Object source = e.getSource();

        if (source.equals(exit)) {
            exitController.execute();
        } else if (source.equals(goToWar)) {
            warGoToWarController.execute(currstate.getGame());
        } else if (source.equals(surrender)) {
            warSurrenderController.execute(currstate.getGame());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        WarGameState currState = warOccurViewModel.getState();
        showPlayerHand(currState.getPlayerImages());
        showDealerHand(currState.getDealerImages());
        if (currState.getWentToWar()) {
            if (currState.getGame().goToWar()) {
                JOptionPane.showMessageDialog(this,
                        "You Won the War and Won the double your original wager: $" + 2 * currState.getBet());
            } else if (currState.getGame().playerWins()) {
                JOptionPane.showMessageDialog(this,
                        "You survived the War and Won your 1.5 times your original wager: $" + (currState.getBet() * 3 / 2));
            } else {
                JOptionPane.showMessageDialog(this,
                        "You lost the War and lost double your wager -$" + 2 * currState.getBet());
            }
        } else if (currState.getSurrendered()) {
            JOptionPane.showMessageDialog(this,
                    "You have chosen to surrender the War and forfeited half of your wager: -$" + currState.getBet() / 2);
        } else if (currState.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, currState.getErrorMessage());
        }
    }

    private void showPlayerHand(java.util.List<Image> imgs) {
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
