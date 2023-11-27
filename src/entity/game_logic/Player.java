package entity.game_logic;

import entity.Card;

import java.util.List;

public abstract class Player {
    protected List<Card> hands;

    public List<Card> getHand() {
        return hands;
    }

    void addToHand(Card card) {
        hands.add(card);
    }
}
