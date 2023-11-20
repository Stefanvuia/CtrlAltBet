package view.baccarat;

import interface_adapter.baccarat.BaccaratState;
import interface_adapter.baccarat.BaccaratViewModel;
import view.custom_swing_elements.BaccaratBackgroundPanel;
import view.custom_swing_elements.BlackJackBackgroundPanel;

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

public class BaccaratGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "baccarat during";
    private final BaccaratViewModel baccaratViewModel;

    private final BaccaratBackgroundPanel tablePanel;

    public BaccaratGameView(BaccaratViewModel baccaratViewModel) throws IOException {
        this.baccaratViewModel = baccaratViewModel;
        this.baccaratViewModel.addPropertyChangeListener(this);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        this.tablePanel = new BaccaratBackgroundPanel(baccaratViewModel.IMG_PATH);
        this.add(tablePanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BaccaratState currState = baccaratViewModel.getState();
        if (!currState.getBankerHandImg().isEmpty() && !currState.getPlayerHandImg().isEmpty()) {
            try {
                showHands(currState.getPlayerHandImg(), currState.getBankerHandImg());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(this, currState.getGameMessage());
        }
    }

    private void showHands(List<String>playerImgs, List<String>bankerImgs) throws IOException {
        tablePanel.getLeft().removeAll();
        tablePanel.getRight().removeAll();

        for (int i = 0; i < 3; i++) {
            URL urlP;
            URL urlB;
            JLabel label;
            Image imageP = null;
            Image imageB = null;
            if (i < 2) {
                urlP = new URL(playerImgs.get(i));
                urlB = new URL(bankerImgs.get(i));
                imageP = ImageIO.read(urlP).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
                imageB = ImageIO.read(urlB).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            } else if (playerImgs.size() > 2 && bankerImgs.size() > 2) {
                urlP = new URL(playerImgs.get(i));
                urlB = new URL(bankerImgs.get(i));
                imageP = ImageIO.read(urlP).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
                imageB = ImageIO.read(urlB).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            } else if (playerImgs.size() > 2) {
                urlP = new URL(playerImgs.get(i));
                imageP = ImageIO.read(urlP).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            } else if (bankerImgs.size() > 2) {
                urlB = new URL(bankerImgs.get(i));
                imageB = ImageIO.read(urlB).getScaledInstance(108, 157, Image.SCALE_SMOOTH);
            }

            if (imageP != null) {
                label = new JLabel();
                label.setIcon(new ImageIcon(imageP));
                tablePanel.getLeft().add(label);
            }
            if (imageB != null) {
                label = new JLabel();
                label.setIcon(new ImageIcon(imageB));
                tablePanel.getRight().add(label);
            }
        }
    }
}
