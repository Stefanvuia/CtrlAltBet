package view.launch_menu;


import interface_adapter.launch_menu.WelcomeViewModel;
import interface_adapter.launch_menu.buttons.UserButtonsController;
import view.custom_swing_elements.GreenCustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel implements ActionListener {

    public final String viewName = "welcome";

    private final WelcomeViewModel welcomeViewModel;

    private final UserButtonsController userButtonsController;
    private final JButton logIn;
    private final JButton signUp;

    /**
     * A window with a title and a JButton.
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
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            userButtonsController.execute(welcomeViewModel.LOGIN_LABEL);
        } else if (evt.getSource().equals(signUp)) {
            userButtonsController.execute(welcomeViewModel.SIGN_UP_LABEL);
        }
    }
}