package view.launch_menu;

import interface_adapter.launch_menu.WelcomeViewModel;
import interface_adapter.launch_menu.buttons.UserButtonsController;
import view.custom_swing_elements.GreenCustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The graphical representation of the welcome view for the application.
 * Provides options for users to navigate to the login or sign-up views.
 */
public class WelcomeView extends JPanel implements ActionListener {

    /**
     * The name of the view, used for identification.
     */
    public final String viewName = "welcome";

    /**
     * The ViewModel associated with the welcome functionality.
     */
    private final WelcomeViewModel welcomeViewModel;

    /**
     * The controller for handling user interactions with buttons.
     */
    private final UserButtonsController userButtonsController;

    /**
     * The button for navigating to the login view.
     */
    private final JButton logIn;

    /**
     * The button for navigating to the sign-up view.
     */
    private final JButton signUp;

    /**
     * Creates a new instance of the WelcomeView.
     *
     * @param welcomeViewModel      The ViewModel associated with the welcome functionality.
     * @param userButtonsController The controller for handling user interactions with buttons.
     */
    public WelcomeView(WelcomeViewModel welcomeViewModel, UserButtonsController userButtonsController) {
        this.welcomeViewModel = welcomeViewModel;
        this.userButtonsController = userButtonsController;
        logIn = new GreenCustomButton(welcomeViewModel.LOGIN_LABEL);
        signUp = new GreenCustomButton(welcomeViewModel.SIGN_UP_LABEL);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(53, 70, 62));
        buttons.add(logIn);
        buttons.add(signUp);

        logIn.addActionListener(this);
        signUp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.setBackground(new Color(53, 70, 62));
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt The ActionEvent triggered by a button click.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            userButtonsController.execute(welcomeViewModel.LOGIN_LABEL);
        } else if (evt.getSource().equals(signUp)) {
            userButtonsController.execute(welcomeViewModel.SIGN_UP_LABEL);
        }
    }
}
