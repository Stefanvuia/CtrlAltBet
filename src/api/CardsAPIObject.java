package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class CardsAPIObject implements CardsAPIInterface{

    public CardsAPIObject(){}

    /**
     * Returns a JSONArray with the cards drawn from the deck corresponding to the id
     *
     * @param id    a valid deckId generated from shuffle()
     * @param cards the number of cards to draw
     * @return      a JSONArray constructed from the API response for the cards drawn
     */
    @Override
    public JSONArray draw(String id, int cards) {
        return (JSONArray) deckHelper(id + "/draw/?count=" + cards).get("cards");
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
}
