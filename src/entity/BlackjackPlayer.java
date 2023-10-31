package entity;

import java.util.List;

public class BlackjackPlayer extends Player {
    private final int bet;
    private final String username;

    public BlackjackPlayer(int bet, String username, List<Card> hands) {
        this.hands = hands;
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
