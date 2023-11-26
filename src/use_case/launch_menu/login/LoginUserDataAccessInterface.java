package use_case.launch_menu.login;


import entity.user.User;
public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    User getUserByName(String username);

    boolean validatePassword(String identifier, String password);

}
