package data_access;

import entity.user.User;
import use_case.launch_menu.login.LoginUserDataAccessInterface;
import use_case.launch_menu.signup.SignupUserDataAccessInterface;
import use_case.launch_menu.update.UpdateUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, UpdateUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
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
}
