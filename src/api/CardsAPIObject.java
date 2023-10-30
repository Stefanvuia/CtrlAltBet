package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class CardsAPIObject implements CardsAPIInterface{

    public CardsAPIObject(){};
    @Override
    public JSONArray draw(String id, int cards) {
        return (JSONArray) deckHelper(id + "/draw/?count=" + cards).get("cards");
    }

    @Override
    public String shuffleNew(int decks) {
        return (String) deckHelper("new/shuffle/?deck_count=" + decks).get("deck_id");
    }

    public JSONObject deckHelper(String call) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://www.deckofcardsapi.com/api/deck/%s", call))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            return new JSONObject(responseBody);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
