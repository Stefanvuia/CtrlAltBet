package use_case.games.war;

import entity.cards.Card;
import entity.cards.StandardCard;
import use_case.games.CardsAPIInterface;

import java.util.ArrayList;
import java.util.List;

public class WarTestAPIObject implements CardsAPIInterface {
    private final boolean win;
    private boolean war;

    public WarTestAPIObject(boolean war, boolean win) {
        this.war = war;
        this.win = win;
    }


    @Override
    public List<Card> draw(String deckId, int cards) {
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < cards; i++) {
            newCards.add(new StandardCard(getValue(), ""));
        }
        return newCards;
    }

    @Override
    public Card draw(String deckId) {
        return new StandardCard(getValue(), "");
    }

    @Override
    public String shuffleNew(int decks) {
        return "qwerty";
    }


    private String getValue() {
        if (war) {
            return "JACK";
        } else if (win) {
            this.war = true;
            return "QUEEN";
        } else {
            this.war = true;
            return "10";
        }
    }
}