package entity.game_logic;

import entity.Card;
import entity.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarDealerTest {
    private WarDealer dealer;
    private Card card;

    @BeforeEach
    void setUp() {
        dealer = new WarDealer();
        card = new StandardCard("8", "https://www.deckofcardsapi.com/static/img/8C.png");
    }

    @Test
    void getHand() {
        assert dealer.getHand().isEmpty();
    }

    @Test
    void addToHand() {
        dealer.addToHand(card);
        assert dealer.getHand().size() == 1;
    }
}
