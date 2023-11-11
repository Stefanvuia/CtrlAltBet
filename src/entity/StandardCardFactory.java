package entity;

import org.json.JSONArray;

public class StandardCardFactory implements CardFactory{

    public StandardCardFactory(){}
    @Override
    public Card create(JSONArray input, int index) {
        String value = input.getJSONObject(index).getString("value");
        String img = input.getJSONObject(index).getString("image");
        return new StandardCard(value, img);
    }
}
