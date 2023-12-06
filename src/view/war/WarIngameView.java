package view.war;

import interface_adapter.war.WarGameState;
import interface_adapter.war.war_logic.WarIngameViewModel;
import tools.GridBagUtils;
import view.custom_swing_elements.BlackJackBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class WarIngameView extends JPanel implements PropertyChangeListener {
    public final String viewName = "war during";
    private final WarIngameViewModel warIngameViewModel;
    private final BlackJackBackgroundPanel tablePanel;

    public WarIngameView(WarIngameViewModel warIngameViewModel) throws IOException {
        this.warIngameViewModel = warIngameViewModel;
        this.warIngameViewModel.addPropertyChangeListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);
        // table
        this.tablePanel = new BlackJackBackgroundPanel(warIngameViewModel.IMG_PATH);
        gridBagUtils.addComponentWithConstraints(tablePanel, 0, 0, 8, 7, 1, 1);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        WarGameState currState = warIngameViewModel.getState();
        showPlayerHand(currState.getPlayerImages());
        showDealerHand(currState.getDealerImages());
        if (currState.getGame().playerWins()) {
            JOptionPane.showMessageDialog(this, "You win!");
        } else {
            JOptionPane.showMessageDialog(this, "You lost");
        }

    }

    private void showPlayerHand(java.util.List<Image> imgs) {
        tablePanel.getBottom().removeAll();
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(imgs.get(0)));
        tablePanel.getBottom().add(label);

        revalidate();
        repaint();
    }

    private void showDealerHand(List<Image> imgs) {
        tablePanel.getTop().removeAll();
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(imgs.get(0)));
        tablePanel.getTop().add(label);
        revalidate();
        repaint();
    }
}
