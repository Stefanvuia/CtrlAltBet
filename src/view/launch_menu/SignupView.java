package view.launch_menu;

import interface_adapter.launch_menu.sign_up.SignUpViewModel;
import interface_adapter.launch_menu.sign_up.SignupState;
import interface_adapter.launch_menu.sign_up.UserSignupController;
import tools.GridBagUtils;
import view.custom_swing_elements.*;
import interface_adapter.launch_menu.buttons.UserButtonsController;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "sign up";
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

    private final UserButtonsController userButtonsController;

    private final JButton signUp;
    private final JButton cancel;


    /**
     * A window with a title and a JButton.
     */
    public SignupView(UserSignupController controller, SignUpViewModel signUpViewModel, UserButtonsController userButtonsController) {
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

    private void textFieldStyleHelper (JTextField textField) {
        textField.setFont(new Font("Courier", Font.BOLD, 28));
        textField.setBorder(new CompoundBorder(new LineBorder(new Color(144, 227, 154), 1), new EmptyBorder(10, 10, 10, 10)));
        textField.setOpaque(true);
        textField.setBackground(new Color(53, 70, 62));
        textField.setForeground(new Color(144, 227, 154));
    }
}