package entity;

import org.json.JSONArray;

public interface CardFactory {
    Card create(JSONArray input, int index);
}
