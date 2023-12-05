package api;

import entity.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardsAPIObjectTest {
    private CardsAPIObject api;
    private String deckId;

    @BeforeEach
    void setUp() {
        api = new CardsAPIObject();
        deckId = "v9jsqqs52qyh";
    }

    @Test
    void draw() {
        assert api.draw(deckId) != null;
    }

    @Test
    void testDraw() {
        List<Card> cards = api.draw(deckId, 2);
        assert !cards.isEmpty();
        for(Card card: cards) {
            assert card != null;
        }
    }

    @Test
    void shuffleNew() {
        assert !api.shuffleNew(2).isEmpty();
    }
}