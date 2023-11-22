package users.update;


import entity.User;

public interface UpdateUserDataAccessInterface {

    User getUserByName(String username);
    void save(User requestModel);
}
