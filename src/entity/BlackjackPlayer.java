package entity;

import java.util.ArrayList;
import java.util.List;

public class BlackjackPlayer extends Player {
    private final int bet;
    private final String username;

    public BlackjackPlayer(int bet, String username) {
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
