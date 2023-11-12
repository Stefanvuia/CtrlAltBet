package use_case;

import entity.Card;

import java.util.List;

public interface CardsAPIInterface {
    List<Card> draw(String deckId, int cards);

    Card draw(String deckId);

    String shuffleNew(int decks);
}
