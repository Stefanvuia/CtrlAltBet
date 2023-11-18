package users;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User requestModel);
}
