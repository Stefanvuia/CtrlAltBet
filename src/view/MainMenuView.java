package view;

import interface_adapter.menu.launch_game.LaunchController;
import interface_adapter.menu.launch_game.LaunchState;
import interface_adapter.menu.launch_game.LaunchViewModel;
import view.custom_elements.GreenCustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "main menu";

    private final LaunchViewModel launchViewModel;

    private final LaunchController launchController;

    final JButton blackjack;

    final JButton baccarat;

    final JButton war;

    final JButton account;

    public MainMenuView(LaunchViewModel launchViewModel, LaunchController launchController) {
        this.launchController = launchController;
        this.launchViewModel = launchViewModel;
        this.launchViewModel.addPropertyChangeListener(this);

        LaunchState currState = launchViewModel.getState();

        blackjack = new GreenCustomButton(launchViewModel.BLACKJACK_LABEL);
        baccarat = new GreenCustomButton(launchViewModel.BACCARAT_LABEL);
        war = new GreenCustomButton(launchViewModel.WAR_LABEL);
        account = new GreenCustomButton(launchViewModel.ACCOUNT_LABEL);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;

        this.add(blackjack, gbc);
        gbc.gridx++;
        this.add(baccarat, gbc);
        gbc.gridy++;
        this.add(account, gbc);
        gbc.gridx--;
        this.add(war, gbc);

        blackjack.addActionListener(e ->
                launchController.execute(currState.getUsername(), launchViewModel.BLACKJACK_NAME));

        baccarat.addActionListener(e ->
                launchController.execute(currState.getUsername(), launchViewModel.BACCARAT_NAME));
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
