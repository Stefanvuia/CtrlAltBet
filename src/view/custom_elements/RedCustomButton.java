package view.custom_elements;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RedCustomButton extends JButton {
    private final Color darkRed = new Color(70, 53, 62);
    private final Color lightRed = new Color(227, 144, 154);

    public RedCustomButton(String text) {
        super(text);
        this.setContentAreaFilled(false);
        this.setFont(new Font("Courier", Font.BOLD, 28));
        this.setBorder(new CompoundBorder(new LineBorder(lightRed, 1), new EmptyBorder(10, 10, 10, 10)));
        this.setOpaque(true);
        this.setFocusPainted(false);
        this.setBackground(darkRed);
        this.setForeground(lightRed);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(darkRed);
                e.getComponent().setForeground(lightRed);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setBackground(lightRed);
                e.getComponent().setForeground(darkRed);
            }
        });
    }
}

