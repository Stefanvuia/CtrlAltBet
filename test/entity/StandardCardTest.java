package entity;

import entity.cards.Card;
import entity.cards.CardBack;
import entity.cards.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardCardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new StandardCard("8", "https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    void getValue() {
        assert card.getValue().equals("8");
    }

    @Test
    void getImg() {
        assert card.getImg().equals("https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    void getBackImg() {
        card = new CardBack();
        assert card.getValue().equals("0");
    }
}