package use_case.account_menu.history;

import java.util.ArrayList;

public interface HistoryDataAccessInterface {
    void addPayout(String username, String game, double amount);

    ArrayList<Double> getPayouts(String username, String game);

    boolean existsByName(String identifier);

    void addUser(String username);
}
