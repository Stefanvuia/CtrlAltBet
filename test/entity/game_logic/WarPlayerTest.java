package entity.game_logic;

import entity.cards.Card;
import entity.cards.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarPlayerTest {
    private WarPlayer player;
    private Card card;

    @BeforeEach
    void setUp() {
        player = new WarPlayer(
                100,
                "cakev"
        );
        card = new StandardCard("8", "https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    void getHand() {
        assert player.getHand().isEmpty();
    }

    @Test
    void addToHand() {
        player.addToHand(card);
        assert player.getHand().size() == 1;
    }

    @Test
    void getBet() {
        assert player.getBet() == 100;
    }

    @Test
    void getUsername() {
        assert player.getUsername().equals("cakev");
    }
}
