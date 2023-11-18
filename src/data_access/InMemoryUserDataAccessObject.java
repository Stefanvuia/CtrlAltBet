package data_access;

import entity.User;
import use_case.signup.SignupUserDataAccessInterface;
import users.UserSignupDsData;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<String, UserSignupDsData> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(UserSignupDsData user) {
        users.put(user.getName(), user);
    }
}
