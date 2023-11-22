package view.custom_elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BaccaratBackgroundPanel extends JPanel {
    private final BufferedImage image;
    private final JPanel left = new JPanel();
    private final JPanel right = new JPanel();

    private final GridBagConstraints gbc = new GridBagConstraints();
    public BaccaratBackgroundPanel(String path) throws IOException {
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        layout.setConstraints(this, gbc);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        left.setOpaque(false);
        this.add(left, gbc);

        gbc.gridx++;
        right.setOpaque(false);
        this.add(right, gbc);


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

    public JPanel getLeft() {
        return left;
    }

    public JPanel getRight() {
        return right;
    }
}