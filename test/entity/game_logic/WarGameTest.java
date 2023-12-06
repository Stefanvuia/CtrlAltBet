package entity.game_logic;

import entity.cards.Card;
import entity.cards.StandardCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WarGameTest {
    private Player player;
    private Player dealer;
    private String deckId;

    private WarGame game;
    private Card qCard;

    private Card kCard;

    private Card jCard;

    private Card aCard;

    private Card sixCard;
    private final List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        deckId = "qwerty";

        player = new WarPlayer(
                100,
                "cakev"
        );

        dealer = new BlackJackDealer();

        game = new WarGame(
                player,
                dealer,
                deckId
        );
        jCard = new StandardCard("JACK", "");
        qCard = new StandardCard("QUEEN", "");
        kCard = new StandardCard("KING", "");
        aCard = new StandardCard("ACE", "");
        sixCard = new StandardCard("6", "");

        cards.add(aCard);
        cards.add(jCard);
    }

    @Test
    void sumHand() {
        player.addToHand(sixCard);
        assert game.sumHand(player) == 6;

        player.addToHand(jCard);
        assert game.sumHand(player) == 11;

        player.addToHand(qCard);
        assert game.sumHand(player) == 12;

        player.addToHand(kCard);
        assert game.sumHand(player) == 13;

        player.addToHand(aCard);
        assert game.sumHand(player) == 14;
    }

    @Test
    void addToHand() {
        game.addToHand(player, jCard);
        assert player.getHand().size() == 1;
        assert player.getHand().get(0).equals(jCard);
    }


    @Test
    void playerWins() {
        player.addToHand(aCard);
        player.addToHand(jCard);
        dealer.addToHand(aCard);
        dealer.addToHand(sixCard);

        assert game.playerWins();
    }
    @Test
    void goToWar() {
        player.addToHand(aCard);
        dealer.addToHand(aCard);
        assert game.goToWar();

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
