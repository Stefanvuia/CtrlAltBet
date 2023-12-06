package view.custom_swing_elements;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GreenCustomButton extends JButton {
    private final Color darkGreen = new Color(53, 70, 62);
    private final Color lightGreen = new Color(144, 227, 154);

    public GreenCustomButton(String text) {
        super(text);
        this.setContentAreaFilled(false);
        this.setFont(new Font("Courier", Font.BOLD, 28));
        this.setBorder(new CompoundBorder(new LineBorder(lightGreen, 1), new EmptyBorder(10, 10, 10, 10)));
        this.setOpaque(true);
        this.setFocusPainted(false);
        this.setBackground(darkGreen);
        this.setForeground(lightGreen);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(darkGreen);
                e.getComponent().setForeground(lightGreen);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setBackground(lightGreen);
                e.getComponent().setForeground(darkGreen);
            }
        });
    }
}
