package users.signup;

import entity.user.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User requestModel);
}
