package view;


import entity.User;
import interface_adapter.UserViewModel;
import interface_adapter.update.UserUpdateController;
import tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

public class BalanceInfoView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The controller
     */

    private final JButton deposit = new JButton("Deposit");
    private final JButton withdraw = new JButton("Withdraw");
    private final JButton back = new JButton("Back");
    private final JTextField balance = new JTextField(15);

    private UserViewModel userViewModel;
    private UserUpdateController userUpdateController;
    /**
     * A window with a title and a JButton.
     */
    public BalanceInfoView(UserUpdateController userUpdateController,UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
        this.userViewModel.addPropertyChangeListener(this);
        this.userUpdateController = userUpdateController;
        JLabel title = new JLabel("BALANCE INFO");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel balanceInfo = new LabelTextPanel(
                new JLabel("Balance"), balance);
        balance.setEditable(false);
        JPanel buttons = new JPanel();
        buttons.add(deposit);
        buttons.add(withdraw);
        buttons.add(back);
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        back.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(balanceInfo);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getSource().equals(deposit)) {
            String value = JOptionPane.showInputDialog(this, "Enter deposit amount");
            System.out.println("deposit value:" + value);
            //TODO: check value is only digits
            //TODO: check value within balance boundary
            if (!Tools.isEmpty(value)) {
                userViewModel.deposit(Integer.parseInt(value));
                userUpdateController.updateUser(userViewModel.getCurrentUser(), userViewModel.getBalance());

            }
        } else if (evt.getSource().equals(withdraw)) {
            String value = JOptionPane.showInputDialog(this, "Enter withdraw amount");
            System.out.println("withdraw value:" + value);
            if (!Tools.isEmpty(value)) {
                userViewModel.withdraw(Integer.parseInt(value));
                userUpdateController.updateUser(userViewModel.getCurrentUser(), userViewModel.getBalance());

            } // TODO: verify value is number
        } else if (evt.getSource().equals(back)) {
            userViewModel.setState(UserViewModel.LoginState.ACCOUNT_INFO);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("balance")) {
            int value = (int) evt.getNewValue();
            balance.setText(String.valueOf(value));
        }
    }
}