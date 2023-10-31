package entity;

import java.util.List;

public class BlackjackDealer extends Player {
    public BlackjackDealer(List<Card> hands) {
        this.hands = hands;
    }

    public List<Card> getHand() {
        return super.getHand();
    }

    public void addToHand(Card card) {
        super.addToHand(card);
    }
}
