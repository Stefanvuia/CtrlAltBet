package entity.game_logic;

import entity.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class WarPlayer extends Player{
    private final int bet;
    private final String username;

    public WarPlayer(int bet, String username) {
        this.hands = new ArrayList<Card>();
        this.bet = bet;
        this.username = username;
    }

    public List<Card> getHand() {
        return super.getHand();
    }

    public void addToHand(Card card) {
        super.addToHand(card);
    }

    public int getBet() {
        return this.bet;
    }

    public String getUsername() {
        return this.username;
    }
}
