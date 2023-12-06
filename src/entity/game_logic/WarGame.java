package entity.game_logic;

import entity.cards.Card;

import java.util.List;

public class WarGame implements WarGameInterface {
    Player user;
    Player dealer;
    String deckId;

    public WarGame(Player user, Player dealer, String deckId) {
        this.user = user;
        this.dealer = dealer;
        this.deckId = deckId;
    }

    @Override
    public int sumHand(Player player) {
        List<Card> hand = player.getHand();
        int value;
        Card card = hand.get(hand.size() - 1);
        if (card.getValue().equals("JACK")) {
            value = 11;
        } else if (card.getValue().equals("QUEEN")) {
            value = 12;
        } else if (card.getValue().equals("KING")) {
            value = 13;
        } else if (card.getValue().equals("ACE")) {
            value = 14;
        } else {
            value = Integer.parseInt(card.getValue());
        }
        return value;
    }

    @Override
    public void addToHand(Player player, Card card) {
        player.addToHand(card);
    }

    @Override
    public boolean goToWar() {
        return sumHand(this.user) == sumHand(this.dealer);
    }

    @Override
    public boolean playerWins() {
        return sumHand(this.user) > sumHand(this.dealer);
    }

    @Override
    public Player getPlayer() {
        return user;
    }

    @Override
    public Player getDealer() {
        return dealer;
    }


    @Override
    public String getDeck() {
        return deckId;
    }
}
