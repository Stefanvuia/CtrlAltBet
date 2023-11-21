package users.login;


import entities.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    User getUserByName(String username);

    boolean validatePassword(String identifier, String password);

}
