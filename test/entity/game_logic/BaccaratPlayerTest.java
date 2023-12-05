package entity.game_logic;

import entity.Card;
import entity.StandardCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class BaccaratPlayerTest {
    private BaccaratPlayer player;
    private Card card;

    @BeforeEach
    void setUp() {
        player = new BaccaratPlayer();
        card = new StandardCard("8", "https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    public void getHand() {
        assert player.getHand().isEmpty();
    }

    @Test
    public void addToHand() {
        player.addToHand(card);
        assert player.getHand().size() == 1;
        assert player.getHand().get(0).equals(card);
    }
}