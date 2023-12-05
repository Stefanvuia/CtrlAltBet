package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageFactoryTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new StandardCard("8", "https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    void create() {
        ImageFactory factory = new ImageFactory();
        assertNotNull(factory.create(card));
    }
}