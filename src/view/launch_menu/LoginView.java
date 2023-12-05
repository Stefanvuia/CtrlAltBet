package view.launch_menu;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.launch_menu.login.LoginState;
import interface_adapter.launch_menu.login.LoginViewModel;
import interface_adapter.launch_menu.login.UserLoginController;
import tools.GridBagUtils;
import view.custom_elements.GreenCustomButton;
import view.custom_elements.GreenCustomJLabel;
import view.custom_elements.GreenCustomPanel;
import interface_adapter.launch_menu.buttons.UserButtonsController;

/**
 * The graphical representation of the login view for the application.
 * Allows users to enter their username and password for authentication.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The name of the view, used for identification.
     */
    public String viewName = "log in";

    /**
     * The ViewModel associated with the login functionality.
     */
    private final LoginViewModel loginViewModel;

    /**
     * The controller for handling user interactions with buttons.
     */
    private final UserButtonsController userButtonsController;

    /**
     * The text field for entering the username.
     */
    final JTextField username = new JTextField(15);

    /**
     * The password field for entering the user's password.
     */
    final JPasswordField password = new JPasswordField(15);

    /**
     * The login button for initiating the login process.
     */
    final JButton logIn;

    /**
     * The cancel button for canceling the login process.
     */
    final JButton cancel;

    /**
     * The controller responsible for handling user login operations.
     */
    private final UserLoginController userLoginController;

    /**
     * Creates a new instance of the LoginView.
     *
     * @param userLoginController  The controller for handling user login operations.
     * @param loginViewModel       The ViewModel associated with the login functionality.
     * @param userButtonsController The controller for handling user interactions with buttons.
     */
    public LoginView(UserLoginController userLoginController, LoginViewModel loginViewModel,
                     UserButtonsController userButtonsController) {
        this.userLoginController = userLoginController;
        this.loginViewModel = loginViewModel;
        this.userButtonsController = userButtonsController;

        loginViewModel.addPropertyChangeListener(this);

        this.textFieldStyleHelper(username);
        this.textFieldStyleHelper(password);

        logIn = new GreenCustomButton(loginViewModel.LOGIN_LABEL);
        cancel = new GreenCustomButton(loginViewModel.CANCEL_LABEL);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        // setting initial layout constraints
        GridBagLayout layout = new GridBagLayout();
        GridBagUtils gridBagUtils = new GridBagUtils(this);
        this.setLayout(layout);

        JPanel username_label = new GreenCustomPanel();
        username_label.add(new GreenCustomJLabel(loginViewModel.USERNAME_LABEL));
        gridBagUtils.addComponentWithConstraints(username_label, 0, 0, 1, 1, 0.5, 1);

        JPanel password_label = new GreenCustomPanel();
        password_label.add(new GreenCustomJLabel(loginViewModel.PASSWORD_LABEL));
        gridBagUtils.addComponentWithConstraints(password_label, 0, 1, 1, 1, 0.5, 1);

        gridBagUtils.addComponentWithConstraints(cancel, 0, 2, 1, 1, 0, 1);
        gridBagUtils.addComponentWithConstraints(username, 1, 0, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(password, 1, 1, 2, 1, 1, 1);
        gridBagUtils.addComponentWithConstraints(logIn, 1, 2, 2, 1, 1, 1);
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt The ActionEvent triggered by a button click.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(cancel)) {
            userButtonsController.execute();
        } else if (evt.getSource().equals(logIn)) {
            userLoginController.login(username.getText(), String.valueOf(password.getPassword()));
        }
    }

    /**
     * Handles property change events related to the login process.
     *
     * @param evt The PropertyChangeEvent triggered when a property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState currState = loginViewModel.getState();
        if (currState.getError() == null) {
            username.setText(currState.getUsername());
        } else {
            JOptionPane.showMessageDialog(this, currState.getError());
        }
        password.setText("");
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