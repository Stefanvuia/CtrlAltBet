package entity.user;

import entity.cards.Card;
import entity.cards.StandardCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandardCardTest {

    @Test
    public void testGetValue() {
        // Arrange
        String value = "Ace";
        String img = "ace_image.png";
        Card standardCard = new StandardCard(value, img);

        // Act
        String actualValue = standardCard.getValue();

        // Assert
        assertEquals(value, actualValue);
    }

    @Test
    public void testGetImg() {
        // Arrange
        String value = "King";
        String img = "king_image.png";
        Card standardCard = new StandardCard(value, img);

        // Act
        String actualImg = standardCard.getImg();

        // Assert
        assertEquals(img, actualImg);
    }

    @Test
    public void testConstructorValues() {
        // Arrange
        String value = "Queen";
        String img = "queen_image.png";

        // Act
        StandardCard standardCard = new StandardCard(value, img);

        // Assert
        assertEquals(value, standardCard.getValue());
        assertEquals(img, standardCard.getImg());
    }
}