package view;

import interface_adapter.UserViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The controller
     */
    private final JButton blackjack = new JButton("BlackJack");
    private final JButton baccarat = new JButton("Baccarat");
    private final JButton war = new JButton("War");
    private final JButton back = new JButton("Back");

    private UserViewModel userViewModel;

    /**
     * A window with a title and a JButton.
     */
    public HistoryView(UserViewModel userViewModel) {

        this.userViewModel = userViewModel;

        JLabel title = new JLabel("Betting History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("See user's betting history of:");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        buttons.add(blackjack);
        buttons.add(baccarat);
        buttons.add(war);
        buttons.add(back);
        blackjack.addActionListener(this);
        baccarat.addActionListener(this);
        war.addActionListener(this);
        back.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(subtitle);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getSource().equals(blackjack)) {
            // create blackjack betting history chart
        } else if (evt.getSource().equals(baccarat)) {
            // create baccarat betting history chart
        } else if (evt.getSource().equals(war)) {
            // create war betting history chart
        } else if (evt.getSource().equals(back)) {
            userViewModel.setState(UserViewModel.LoginState.ACCOUNT_INFO);
        }


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
