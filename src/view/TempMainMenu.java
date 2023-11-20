package view;

import interface_adapter.ViewManagerModel;
import view.custom_swing_elements.GreenCustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempMainMenu extends JPanel{
    public final String viewName = "temp menu";

    final JButton a = new GreenCustomButton("blackjack");

    final JButton b = new GreenCustomButton("baccarat");

    final JButton c = new GreenCustomButton("war (wip)");

    final JButton d = new GreenCustomButton("account (wip)");

    public TempMainMenu(ViewManagerModel viewManagerModel) {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        JPanel aPanel = new JPanel(new BorderLayout(0, 0));
        this.add(a, gbc);
        gbc.gridx++;
        this.add(b, gbc);
        gbc.gridy++;
        this.add(d, gbc);
        gbc.gridx--;
        this.add(c, gbc);

        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("bj start");
                viewManagerModel.firePropertyChanged();
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("baccarat start");
                viewManagerModel.firePropertyChanged();
            }
        });
    }
}
