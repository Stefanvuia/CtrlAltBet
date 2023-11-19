package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomPanel extends JPanel {
    public CustomPanel(){
        super();
        this.setBorder(new CompoundBorder(
                new LineBorder(new Color(144, 227, 154)),
                new EmptyBorder(10, 10, 10, 10)));
        this.setBackground(new Color(53, 70, 62));
    }
}
