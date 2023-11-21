package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceInfoView extends JPanel implements ActionListener {

    /**
     * The controller
     */

    private final JButton deposit = new JButton("Deposit");
    private final JButton withdraw = new JButton("Withdraw");
    private final JButton back = new JButton("Back");
    private final JTextField balance = new JTextField(15);


    /**
     * A window with a title and a JButton.
     */
    public BalanceInfoView() {
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

    }

}