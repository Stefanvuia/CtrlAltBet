package use_case;

import use_case.account_menu.history.HistoryDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryHistoryDataAccessObject implements HistoryDataAccessInterface {
    private final Map<String, Map<String, ArrayList<Double>>> history = new HashMap<>();

    @Override
    public void addPayout(String username, String game, double amount) {
        history.get(username).get(game).add(amount);
    }

    @Override
    public ArrayList<Double> getPayouts(String username, String game) {
        return history.get(username).get(game);
    }

    @Override
    public boolean existsByName(String identifier) {
        return history.containsKey(identifier);
    }

    @Override
    public void addUser(String username) {
        ArrayList<Double> bjBets = new ArrayList<>();
        ArrayList<Double> bcBets = new ArrayList<>();
        ArrayList<Double> wBets = new ArrayList<>();
        Map<String, ArrayList<Double>> bets = new HashMap<>();

        bets.put("blackjack", bjBets);
        bets.put("baccarat", bcBets);
        bets.put("war", wBets);

        history.put(username, bets);
    }
}
