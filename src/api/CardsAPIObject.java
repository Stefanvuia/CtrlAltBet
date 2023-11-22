package api;

import entity.Card;
import entity.StandardCard;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.CardsAPIInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardsAPIObject implements CardsAPIInterface {

    public CardsAPIObject(){}

    /**
     * Returns a list of cards with the cards drawn from the deck corresponding to the id
     *
     * @param deckId    a valid deckId generated from shuffleNew()
     * @param cards the number of cards to draw
     * @return      a list of cards constructed from the API response
     */
    @Override
    public List<Card> draw(String deckId, int cards) {
        JSONArray output = (JSONArray) deckHelper(deckId + "/draw/?count=" + cards).get("cards");
        List<Card> newCards = new ArrayList<Card>(cards);
        for (int i = 0; i < output.length(); i++) {
            newCards.add(cardHelper(output, i));
        }

        return newCards;
    }

    /**
     * Return a single card drawn from the deck corresponding to the id
     *
     * @param deckId    a valid deckId generated from shuffleNew()
     * @return  a Card constructed from the API response
     */
    @Override
    public Card draw(String deckId) {
        JSONArray output = (JSONArray) deckHelper(deckId + "/draw/?count=1").get("cards");
        return cardHelper(output, 0);
    }

    /**
     * Returns a String with the deckId from the generated deck
     *
     * @param decks the number of decks to create and shuffle
     * @return      a String of the deckId from the API response
     */
    @Override
    public String shuffleNew(int decks) {
        return (String) deckHelper("new/shuffle/?deck_count=" + decks).get("deck_id");
    }

    /**
     * Private helper method which executes the API call, only meant to be
     * called from other methods within the class
     *
     * @param call  a String which details the specific API call
     * @return      a JSONObject constructed from the API response body
     */
    private JSONObject deckHelper(String call) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://www.deckofcardsapi.com/api/deck/%s", call))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            return new JSONObject(responseBody);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Private helper method which constructs the card, only meant to be called from draw(). draw() first calls
     * the API to create a JSONArray with the cards drawn, each JSONObject within the JSONArray contains the
     * data for one card which can be accessed by its index.
     *
     * @param input a JSONArray from the API draw call
     * @param index the index of the desired JSONObject from the input
     * @return      a Card constructed from the JSONObject at the specified index from the input
     */
    private Card cardHelper(JSONArray input, int index) {
        String value = input.getJSONObject(index).getString("value");
        String img = input.getJSONObject(index).getString("image");
        return new StandardCard(value, img);
    }
}
