package data_access;

import entity.user.User;
import entity.user.UserHistory;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.account_menu.reset_graph.ResetDataAccessInterface;
import use_case.launch_menu.login.LoginUserDataAccessInterface;
import use_case.launch_menu.signup.SignupUserDataAccessInterface;
import use_case.account_menu.update.UpdateUserDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        UpdateUserDataAccessInterface, HistoryDataAccessInterface, ResetDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, UserHistory> usersHistory = new HashMap<>();

    @Override
    public void addPayout(String username, String game, double amount) {
        if (!existsByName(username)){
            addUser(username);
        }
        usersHistory.get(username).setPayouts(game, amount);
    }

    @Override
    public ArrayList<Double> getPayouts(String username, String game) {
        return null;
    }

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    public boolean existsByNameHistory(String identifier) {
        return usersHistory.containsKey(identifier);
    }

    @Override
    public void addUser(String username) {

    }

    public User getUserByName(String username) {
        return users.get(username);
    }

    public boolean validatePassword(String username, String password) {
        User user = users.get(username);
        return user.getPassword().equals(password);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    public void saveHistory(UserHistory user) {
        usersHistory.put(user.getUsername(), user);
    }

    @Override
    public void reset(String username) throws IOException {

    }
}
