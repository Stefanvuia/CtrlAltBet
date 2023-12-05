package view.custom_swing_elements;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class FundsField extends JFormattedTextField {
    private final Color darkGreen = new Color(53, 70, 62);
    private final Color lightGreen = new Color(144, 227, 154);
    public FundsField(NumberFormatter format) {
        super(format);
        this.setFont(new Font("Courier", Font.BOLD, 28));
        this.setBorder(new CompoundBorder(new LineBorder(lightGreen, 1), new EmptyBorder(10, 10, 10, 10)));
        this.setOpaque(true);
        this.setBackground(darkGreen);
        this.setForeground(lightGreen);
        this.setValue(0);
        this.setColumns(10);
    }
}
