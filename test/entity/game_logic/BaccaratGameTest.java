package entity.game_logic;

import entity.cards.Card;
import entity.cards.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BaccaratGameTest {
    private Player player;
    private Player banker;
    private String deckId;
    private BaccaratGame game;

    private Card jCard;

    private Card aCard;

    private Card sixCard;
    private List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        player = new BaccaratPlayer();
        banker = new BaccaratPlayer();
        deckId = "qwerty";
        jCard = new StandardCard("JACK", "");
        aCard = new StandardCard("ACE", "");
        sixCard = new StandardCard("6", "");

        cards.add(aCard);
        cards.add(jCard);

        game = new BaccaratGame(player, banker, deckId);
    }

    @Test
    void sumHand() {
        assert game.sumHand(player) == 0;
        assert game.sumHand(banker) == 0;

        player.addToHand(jCard);
        player.addToHand(aCard);
        assert game.sumHand(player) == 1;

        player.addToHand(sixCard);
        assert game.sumHand(player) == 7;

        player.addToHand(sixCard);
        assert game.sumHand(player) == 3;
    }

    @Test
    void addToHand() {
        game.addToHand(player, jCard);
        assert player.getHand().size() == 1;
        assert player.getHand().get(0).equals(jCard);
    }

    @Test
    void testAddToHand() {
        game.addToHand(banker, cards);
        assert banker.getHand().size() == 2;
    }

    @Test
    void getDeck() {
        assert game.getDeck().equals(deckId);
    }
}