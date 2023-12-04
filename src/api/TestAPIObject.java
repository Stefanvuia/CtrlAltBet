package api;

import entity.Card;
import entity.StandardCard;
import use_case.games.CardsAPIInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestAPIObject implements CardsAPIInterface {

    @Override
    public List<Card> draw(String deckId, int cards) {
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < cards; i++) {
            newCards.add(new StandardCard("JACK", ""));
        }
        return newCards;
    }

    @Override
    public Card draw(String deckId) {
        return new StandardCard("JACK", "");
    }

    @Override
    public String shuffleNew(int decks) {
        return "qwerty";
    }
}
