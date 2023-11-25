package view;

import interface_adapter.UserViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountInfoView extends JPanel implements ActionListener {
    private final UserViewModel userViewModel;

    /**
     * The controller
     */
    private final JButton checkBalance = new JButton("Check Balance");
    private final JButton checkHistory = new JButton("Check History");
    private final JButton back = new JButton("Back");

    /**
     * A window with a title and a JButton.
     */
    public AccountInfoView(UserViewModel userViewModel) {

        this.userViewModel = userViewModel;
        JLabel title = new JLabel("ACCOUNT INFO");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        buttons.add(checkBalance);
        buttons.add(checkHistory);
        buttons.add(back);
        checkBalance.addActionListener(this);
        checkHistory.addActionListener(this);
        back.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getSource().equals(checkBalance)) {
            userViewModel.setState(UserViewModel.LoginState.BALANCE_INFO);
        } else if (evt.getSource().equals(checkHistory)) {
            userViewModel.setState(UserViewModel.LoginState.BET_HISTORY);
        } else if (evt.getSource().equals(back)) {
            userViewModel.setState(UserViewModel.LoginState.LOGGED_IN);
        }

    }

}