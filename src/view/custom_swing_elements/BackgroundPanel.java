package view.custom_swing_elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JPanel {
    BufferedImage image;
    public BackgroundPanel(String path) throws IOException {
        super();
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}
