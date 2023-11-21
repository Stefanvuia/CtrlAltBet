package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import interface_adapter.UserViewModel;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    /**
     * The username chosen by the user
     */


     private final JTextField username = new JTextField(15); // maybe a JLabel?
    /**
     * A window with a title and a JButton.
     */
    private JButton logOut = new JButton("Log out");
    private JButton changePassword = new JButton("Change password");
    private UserViewModel userViewModel;
    public LoggedInView(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
        this.userViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged-in Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logOut = new JButton("Log out");
        JButton changePassword = new JButton("Change password");

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), username);
        username.setEditable(false);

        JPanel buttons = new JPanel();
        buttons.add(logOut);
        buttons.add(changePassword);

        logOut.addActionListener(this);
        changePassword.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource().equals(logOut)) {
            userViewModel.setState(UserViewModel.LoginState.WELCOME);
        } else if (evt.getSource().equals(changePassword)) {
            System.out.println("change password");
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.print(evt);
        if (evt.getPropertyName().equals("user")) {
            String name = (String) evt.getNewValue();
            username.setText(name);
        }
    }
}