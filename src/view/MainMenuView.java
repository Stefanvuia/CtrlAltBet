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

        blackjack = new GreenCustomButton(launchViewModel.BLACKJACK_LABEL);
        baccarat = new GreenCustomButton(launchViewModel.BACCARAT_LABEL);
        war = new GreenCustomButton(launchViewModel.WAR_LABEL);
        account = new GreenCustomButton(launchViewModel.ACCOUNT_LABEL);

        blackjack.addActionListener(this);
        baccarat.addActionListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        // blackjack button
        gridBagUtils.addComponentWithConstraints(blackjack, 0, 0, 1, 1, 1, 1);

        // baccarat button
        gridBagUtils.addComponentWithConstraints(baccarat, 1, 0, 1, 1, 1, 1);

        // account button
        gridBagUtils.addComponentWithConstraints(account, 1, 1, 1, 1, 1, 1);

        // war button
        gridBagUtils.addComponentWithConstraints(war, 0, 1, 1, 1, 1, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LaunchState currState = launchViewModel.getState();
        if (e.getSource().equals(blackjack)) {
            launchController.execute(currState.getUsername(), launchViewModel.BLACKJACK_NAME);
        } else if (e.getSource().equals(baccarat)) {
            launchController.execute(currState.getUsername(), launchViewModel.BACCARAT_NAME);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}