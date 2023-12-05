package entity.game_logic;

import entity.Card;
import entity.StandardCard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {
    private Player player;
    private Player dealer;
    private String deckId;
    private BlackJackGame game;

    private Card jCard;

    private Card aCard;

    private Card sixCard;
    private List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        deckId = "qwerty";

        player = new BlackJackPlayer(
                100,
                "cakev"
        );

        dealer = new BlackJackDealer();

        game = new BlackJackGame(
                player,
                dealer,
                deckId
        );
        jCard = new StandardCard("JACK", "");
        aCard = new StandardCard("ACE", "");
        sixCard = new StandardCard("6", "");

        cards.add(aCard);
        cards.add(jCard);
    }

    @Test
    void sumHand() {
        assert game.sumHand(player) == 0;
        assert game.sumHand(dealer) == 0;

        player.addToHand(jCard);
        player.addToHand(aCard);
        assert game.sumHand(player) == 21;

        player.addToHand(aCard);
        assert game.sumHand(player) == 12;

        player.addToHand(sixCard);
        assert game.sumHand(player) == 18;
    }

    @Test
    void addToHand() {
        game.addToHand(player, jCard);
        assert player.getHand().size() == 1;
        assert player.getHand().get(0).equals(jCard);
    }

    @Test
    void testAddToHand() {
        game.addToHand(dealer, cards);
        assert dealer.getHand().size() == 2;
    }

    @Test
    void userWin() {
        player.addToHand(aCard);
        player.addToHand(jCard);
        dealer.addToHand(aCard);
        dealer.addToHand(sixCard);

        assert game.userWin();
    }

    @Test
    void getPlayer() {
        assert game.getPlayer().equals(player);
    }

    @Test
    void getDealer() {
        assert game.getDealer().equals(dealer);
    }

    @Test
    void getDeck() {
        assert game.getDeck().equals(deckId);
    }
}