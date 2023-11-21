package view;

import interface_adapter.UserViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interface_adapter.UserLoginController;
import users.login.LoginOutputData;

public class LoginView extends JPanel implements ActionListener {
    /**
     * The username chosen by the user
     */
    private UserViewModel userViewModel;
    final JTextField username = new JTextField(15);
    /**
     * The password
     */
    final JPasswordField password = new JPasswordField(15);

    final JButton logIn = new JButton("Log in");
    final JButton cancel = new JButton("Cancel");

    private final UserLoginController userLoginController;
    /**
     * A window with a title and a JButton.
     */
    public LoginView(UserLoginController userLoginController, UserViewModel userViewModel) {
        this.userLoginController = userLoginController;
        this.userViewModel = userViewModel;

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), password);

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(cancel);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource().equals(cancel)) {
            userViewModel.setState(UserViewModel.LoginState.WELCOME);
        } else if (evt.getSource().equals(logIn)) {
            try {
                LoginOutputData response = userLoginController.login(username.getText(), String.valueOf(password.getPassword()));
                userViewModel.setCurrentUser(response.getUser().getName());
                userViewModel.setBalance(response.getUser().getBalance());
                JOptionPane.showMessageDialog(this, "%s Login.".formatted(username.getText()));
                userViewModel.setState(UserViewModel.LoginState.LOGGED_IN);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }

}