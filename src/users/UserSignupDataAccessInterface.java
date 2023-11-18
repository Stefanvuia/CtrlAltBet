package users;

import entity.User;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User requestModel);
}
