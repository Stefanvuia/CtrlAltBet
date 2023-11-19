package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class CustomButton extends JButton {
    private Color darkGreen = new Color(53, 70, 62);
    private Color lightGreen = new Color(144, 227, 154);
    public CustomButton(String text) {
        super(text);
        this.setContentAreaFilled(false);
        this.setFont(new Font("Courier", Font.BOLD, 28));
        this.setBorder(new CompoundBorder(new LineBorder(lightGreen), new EmptyBorder(10, 10, 10, 10)));
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
