package view.welcome;


import view.custom_elements.GreenCustomButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel implements ActionListener {

    public final String viewName = "welcome";

    private final WelcomeViewModel welcomeViewModel;

    private final WelcomeButtonsController welcomeButtonsController;
    private final JButton logIn;
    private final JButton signUp;

    /**
     * A window with a title and a JButton.
     */
    public WelcomeView(WelcomeViewModel welcomeViewModel, WelcomeButtonsController welcomeButtonsController) {
        this.welcomeViewModel = welcomeViewModel;
        this.welcomeButtonsController = welcomeButtonsController;
        logIn = new GreenCustomButton(welcomeViewModel.LOGIN_LABEL);
        signUp = new GreenCustomButton(welcomeViewModel.SIGN_UP_LABEL);

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(signUp);

        logIn.addActionListener(this);
        signUp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            welcomeButtonsController.execute(welcomeViewModel.LOGIN_LABEL);
        } else if (evt.getSource().equals(signUp)) {
            welcomeButtonsController.execute(welcomeViewModel.SIGN_UP_LABEL);
        }
    }
}