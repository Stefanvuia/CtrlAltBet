package use_case.signup;

import entity.User;
import users.UserSignupDsData;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(UserSignupDsData user);
}
