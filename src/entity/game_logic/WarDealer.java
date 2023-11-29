package entity.game_logic;

import entity.Card;

import java.util.ArrayList;
import java.util.List;

public class WarDealer extends Player{

    public WarDealer(){
        this.hands = new ArrayList<Card>();
    }
    public List<Card> getHand() {
        return super.getHand();
    }

    public void addToHand(Card card) {
        super.addToHand(card);
    }
}