package view.custom_elements;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GreenCustomPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    public GreenCustomPanel(){
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        layout.setConstraints(this, gbc);

        this.setFont(new Font("Courier", Font.BOLD, 28));
        this.setOpaque(true);
        this.setBorder(new CompoundBorder(
                new LineBorder(new Color(144, 227, 154),1),
                new EmptyBorder(10, 10, 10, 10)));
        this.setBackground(new Color(53, 70, 62));
    }
}
