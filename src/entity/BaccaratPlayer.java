package entity;

import java.util.ArrayList;
import java.util.List;

public class BaccaratPlayer extends Player{
    public BaccaratPlayer() {
        this.hands = new ArrayList<Card>();
    }

    public List<Card> getHand() {
        return super.getHand();
    }

    public void addToHand(Card card) {
        super.addToHand(card);
    }
}
