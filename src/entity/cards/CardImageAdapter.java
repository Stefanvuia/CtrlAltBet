package entity.cards;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class CardImageAdapter extends BufferedImage {

    public CardImageAdapter(Card card) {
        super(226, 314, BufferedImage.TYPE_INT_ARGB); // Initialize with a dummy size
        // Check if the card has a valid source
        if (card != null && card.getImg() != null && !card.getImg().isEmpty()) {
            try {
                // Read the image from the specified source
                BufferedImage image = ImageIO.read(new URL(card.getImg()));
                // Set the RGB values of the new BufferedImage
                setRGB(0, 0, 226, 314, image.getRGB(0, 0, 226, 314, null, 0, 226), 0, 226);

            } catch (IOException ignored) {}
        }
    }
}
