package use_case;

import org.json.JSONArray;

public interface CardsAPIInterface {
    JSONArray draw(String id, int cards);

    String shuffleNew(int decks);
}
