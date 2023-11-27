package view.custom_elements;

import javax.swing.*;
import java.awt.*;

public class GreenCustomJLabel extends JLabel {
    public GreenCustomJLabel(String text) {
        super(text);
        this.setFont(new Font("Courier", Font.BOLD, 26));
        this.setForeground(new Color(144, 227, 154));
    }
}
