package entity;

import java.util.ArrayList;
import java.util.List;

public class BlackjackDealer extends Player {
    public BlackjackDealer() {
        this.hands = new ArrayList<Card>();;
    }

    public List<Card> getHand() {
        return super.getHand();
    }

    public void addToHand(Card card) {
        super.addToHand(card);
    }
}
