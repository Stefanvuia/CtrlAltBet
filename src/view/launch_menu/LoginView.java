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

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "log in";
    /**
     * The username chosen by the user
     */
    private final LoginViewModel loginViewModel;

    private final UserButtonsController userButtonsController;
    final JTextField username = new JTextField(15);
    /**
     * The password
     */
    final JPasswordField password = new JPasswordField(15);

    final JButton logIn;
    final JButton cancel;

    private final UserLoginController userLoginController;
    /**
     * A window with a title and a JButton.
     */
    public LoginView(UserLoginController userLoginController, LoginViewModel loginViewModel, UserButtonsController userButtonsController) {
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
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(cancel)) {
            userButtonsController.execute();
        } else if (evt.getSource().equals(logIn)) {
            userLoginController.login(username.getText(), String.valueOf(password.getPassword()));
//            try {
//                LoginOutputData response = userLoginController.login(username.getText(), String.valueOf(password.getPassword()));
//                userViewModel.setCurrentUser(response.getUser().getName());
//                userViewModel.setBalance(response.getUser().getBalance());
//                JOptionPane.showMessageDialog(this, "%s Login.".formatted(username.getText()));
//                userViewModel.setState(UserViewModel.LoginState.LOGGED_IN);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e.getMessage());
//            }
        }

    }

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

    private void textFieldStyleHelper (JTextField textField) {
        textField.setFont(new Font("Courier", Font.BOLD, 28));
        textField.setBorder(new CompoundBorder(new LineBorder(new Color(144, 227, 154), 1), new EmptyBorder(10, 10, 10, 10)));
        textField.setOpaque(true);
        textField.setBackground(new Color(53, 70, 62));
        textField.setForeground(new Color(144, 227, 154));
    }
}