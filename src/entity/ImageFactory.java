package entity;

import constants.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageFactory {
    public ImageFactory(){}

    public Image create(Card card) {
        URL url;
        Image image;

        try {
            url = new URL(card.getImg());
            image = ImageIO.read(url).getScaledInstance(Constants.CARD_WIDTH,
                    Constants.CARD_HEIGHT,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {throw new RuntimeException(e);}

        return image;
    }
}
