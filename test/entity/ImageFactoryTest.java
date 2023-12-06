package entity;

import entity.cards.Card;
import entity.cards.CardImageFactory;
import entity.cards.ImageFactory;
import entity.cards.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageFactoryTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new StandardCard("8", "https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    void create() {
        ImageFactory factory = new CardImageFactory();
        assertNotNull(factory.create(card));
    }
}