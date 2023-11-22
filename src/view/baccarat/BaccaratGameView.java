package view.baccarat;

import interface_adapter.baccarat.BaccaratGameState;
import interface_adapter.baccarat.BaccaratGameViewModel;
import view.custom_elements.BaccaratBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class BaccaratGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "baccarat during";
    private final BaccaratGameViewModel baccaratViewModel;

    private final BaccaratBackgroundPanel tablePanel;

    public BaccaratGameView(BaccaratGameViewModel baccaratViewModel) throws IOException {
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
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BaccaratGameState currState = baccaratViewModel.getState();
        if (!currState.getBankerHandImg().isEmpty() && !currState.getPlayerHandImg().isEmpty()) {
            try {
                showHands(currState.getPlayerHandImg(), currState.getBankerHandImg());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(this, currState.getGameMessage());
        }
    }

    private void showHands(List<Image> playerImgs, List<Image> bankerImgs) throws IOException {
        tablePanel.getLeft().removeAll();
        tablePanel.getRight().removeAll();
        JLabel label;

        for (Image image : playerImgs) {
            label = new JLabel();
            label.setIcon(new ImageIcon(image));
            tablePanel.getLeft().add(label);
        }

        for (Image image : bankerImgs) {
            label = new JLabel();
            label.setIcon(new ImageIcon(image));
            tablePanel.getRight().add(label);
        }

        revalidate();
        repaint();
    }
}
