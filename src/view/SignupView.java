package view;

import interface_adapter.UserSignupController;
import interface_adapter.UserViewModel;
import view.custom_elements.GreenCustomButton;
import view.welcome.WelcomeButtonsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SignUpViewModel signUpViewModel;
    /**
     * The username chosen by the user
     */
    private final JTextField username = new JTextField(15);
    /**
     * The password
     */
    private final JPasswordField password = new JPasswordField(15);
    /**
     * The second password to make sure the user understands
     */
    private final JPasswordField repeatPassword = new JPasswordField(15);

    /**
     * The controller
     */
    private final UserSignupController userSignupController;

    private final WelcomeButtonsController welcomeButtonsController;

    private final JButton signUp;
    private final JButton cancel;


    /**
     * A window with a title and a JButton.
     */
    public SignupView(UserSignupController controller, SignUpViewModel signUpViewModel, WelcomeButtonsController welcomeButtonsController) {
        this.userSignupController = controller;
        this.signUpViewModel = signUpViewModel;
        this.welcomeButtonsController = welcomeButtonsController;

        signUpViewModel.addPropertyChangeListener(this);

        signUp = new GreenCustomButton(signUpViewModel.SIGN_UP_LABEL);
        cancel = new GreenCustomButton(signUpViewModel.CANCEL_LABEL);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose password"), password);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter password again"), repeatPassword);


        JPanel buttons = new JPanel();
        buttons.add(signUp);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signUp)) {
            userSignupController.create(username.getText(),
                    String.valueOf(password.getPassword()),
                    String.valueOf(repeatPassword.getPassword()));
        } else if (evt.getSource().equals(cancel)) {
            welcomeButtonsController.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (signUpViewModel.getErrorMessage() == null) {
            JOptionPane.showMessageDialog(this, "%s created.".formatted(username.getText()));
        } else {
            JOptionPane.showMessageDialog(this, signUpViewModel.getErrorMessage());
        }
    }
}