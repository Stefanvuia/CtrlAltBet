package use_case.games.baccarat;

import entity.Card;
import entity.StandardCard;
import use_case.games.CardsAPIInterface;

import java.util.ArrayList;
import java.util.List;

public class BaccaratTestAPIObject implements CardsAPIInterface {
    private static int counter;

    private String valueA;

    private String valueB;

    public BaccaratTestAPIObject(String valueA, String valueB) {
        counter = 0;
        this.valueA = valueA;
        this.valueB = valueB;
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
        return switch (counter) {
            case 5 -> {
                counter++;
                yield "4";
            }
            case 4 -> {
                counter++;
                yield "7";
            }
            case 3 -> {
                counter++;
                yield valueB;
            }
            case 2, 0, 1 -> {
                counter++;
                yield valueA;
            }
            default -> "JACK";
        };
    }
}
