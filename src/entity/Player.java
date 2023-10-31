package entity;

import java.util.List;

public abstract class Player {
    protected List<Card> hands;

    List<Card> getHand() {
        return hands;
    }

    void addToHand(Card card) {
        hands.add(card);
    }
}
