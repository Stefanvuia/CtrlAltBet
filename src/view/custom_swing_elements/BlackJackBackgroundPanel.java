package view.custom_swing_elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlackJackBackgroundPanel extends JPanel {
    private final BufferedImage image;
    private final JPanel top = new JPanel();
    private final JPanel bottom = new JPanel();

    public BlackJackBackgroundPanel(String path) throws IOException {
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        layout.setConstraints(this, gbc);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        top.setOpaque(false);
        this.add(top, gbc);

        gbc.gridy++;
        bottom.setOpaque(false);
        this.add(bottom, gbc);


        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public JPanel getTop() {
        return top;
    }

    public JPanel getBottom() {
        return bottom;
    }
}
