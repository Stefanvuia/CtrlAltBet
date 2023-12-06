package entity.game_logic;

import entity.cards.Card;
import entity.cards.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackJackDealerTest {
    private BlackJackDealer dealer;
    private Card card;

    @BeforeEach
    void setUp() {
        dealer = new BlackJackDealer();
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