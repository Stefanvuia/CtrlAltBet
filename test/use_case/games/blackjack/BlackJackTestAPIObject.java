package use_case.games.blackjack;

import entity.cards.Card;
import entity.cards.StandardCard;
import use_case.games.CardsAPIInterface;

import java.util.ArrayList;
import java.util.List;

public class BlackJackTestAPIObject implements CardsAPIInterface {
    private String value;

    public BlackJackTestAPIObject(String value) {
        this.value = value;
    }

    @Override
    public List<Card> draw(String deckId, int cards) {
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < cards; i++) {
            newCards.add(new StandardCard(value, ""));
        }
        return newCards;
    }

    @Override
    public Card draw(String deckId) {
        return new StandardCard(value, "");
    }

    @Override
    public String shuffleNew(int decks) {
        return "qwerty";
    }
}
