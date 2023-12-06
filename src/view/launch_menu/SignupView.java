package view.launch_menu;

import interface_adapter.launch_menu.buttons.UserButtonsController;
import interface_adapter.launch_menu.sign_up.SignUpViewModel;
import interface_adapter.launch_menu.sign_up.SignupState;
import interface_adapter.launch_menu.sign_up.UserSignupController;
import tools.GridBagUtils;
import view.custom_swing_elements.GreenCustomButton;
import view.custom_swing_elements.GreenCustomJLabel;
import view.custom_swing_elements.GreenCustomPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The graphical representation of the sign-up view for the application.
 * Allows users to create a new account by entering a username and password.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The name of the view, used for identification.
     */
    public String viewName = "sign up";

    /**
     * The ViewModel associated with the sign-up functionality.
     */
    private final SignUpViewModel signUpViewModel;

    /**
     * The text field for entering the desired username.
     */
    private final JTextField username = new JTextField(15);

    /**
     * The password field for entering the user's password.
     */
    private final JPasswordField password = new JPasswordField(15);

    /**
     * The password field for confirming the entered password.
     */
    private final JPasswordField repeatPassword = new JPasswordField(15);

    /**
     * The controller for handling user sign-up operations.
     */
    private final UserSignupController userSignupController;

    /**
     * The controller for handling user interactions with buttons.
     */
    private final UserButtonsController userButtonsController;

    /**
     * The button for initiating the sign-up process.
     */
    private final JButton signUp;

    /**
     * The button for canceling the sign-up process.
     */
    private final JButton cancel;

    /**
     * Creates a new instance of the SignupView.
     *
     * @param controller           The controller for handling user sign-up operations.
     * @param signUpViewModel      The ViewModel associated with the sign-up functionality.
     * @param userButtonsController The controller for handling user interactions with buttons.
     */
    public SignupView(UserSignupController controller, SignUpViewModel signUpViewModel,
                      UserButtonsController userButtonsController) {
        this.userSignupController = controller;
        this.signUpViewModel = signUpViewModel;
        this.userButtonsController = userButtonsController;

        signUpViewModel.addPropertyChangeListener(this);

        signUp = new GreenCustomButton(signUpViewModel.SIGN_UP_LABEL);
        cancel = new GreenCustomButton(signUpViewModel.CANCEL_LABEL);

        this.textFieldStyleHelper(username);
        this.textFieldStyleHelper(password);
        this.textFieldStyleHelper(repeatPassword);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        JPanel username_label = new GreenCustomPanel();
        username_label.add(new GreenCustomJLabel(signUpViewModel.USERNAME_LABEL));
        gridBagUtils.addComponentWithConstraints(username_label, 0, 0, 1, 1, 0.5, 1);

        JPanel password_label = new GreenCustomPanel();
        password_label.add(new GreenCustomJLabel(signUpViewModel.PASSWORD_LABEL));
        gridBagUtils.addComponentWithConstraints(password_label, 0, 1, 1, 1, 0.5, 1);

        JPanel confirm_label = new GreenCustomPanel();
        confirm_label.add(new GreenCustomJLabel(signUpViewModel.PASSWORD_CONFIRM));
        gridBagUtils.addComponentWithConstraints(confirm_label, 0, 2, 1, 1, 0.5, 1);

        gridBagUtils.addComponentWithConstraints(cancel, 0, 3, 1, 1, 0, 1);
        gridBagUtils.addComponentWithConstraints(username, 1, 0, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(password, 1, 1, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(repeatPassword, 1, 2, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(signUp, 1, 3, 2, 1, 1, 1);
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt The ActionEvent triggered by a button click.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signUp)) {
            userSignupController.create(username.getText(),
                    String.valueOf(password.getPassword()),
                    String.valueOf(repeatPassword.getPassword()));
        } else if (evt.getSource().equals(cancel)) {
            userButtonsController.execute();
        }
    }

    /**
     * Handles property change events related to the sign-up process.
     *
     * @param evt The PropertyChangeEvent triggered when a property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState currState = signUpViewModel.getState();
        if (currState.isUserCreated()) {
            JOptionPane.showMessageDialog(this, "%s created.".formatted(username.getText()));
        } else if (currState.getError() != null) {
            JOptionPane.showMessageDialog(this, currState.getError());
        }
        username.setText("");
        password.setText("");
        repeatPassword.setText("");
    }

    /**
     * Helper method to apply a consistent style to text fields.
     *
     * @param textField The JTextField to apply the style to.
     */
    private void textFieldStyleHelper(JTextField textField) {
        textField.setFont(new Font("Courier", Font.BOLD, 28));
        textField.setBorder(new CompoundBorder(new LineBorder(new Color(144, 227, 154), 1),
                new EmptyBorder(10, 10, 10, 10)));
        textField.setOpaque(true);
        textField.setBackground(new Color(53, 70, 62));
        textField.setForeground(new Color(144, 227, 154));
    }
}