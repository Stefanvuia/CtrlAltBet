package use_case.launch_menu.update;


import entity.user.User;

public interface UpdateUserDataAccessInterface {

    User getUserByName(String username);
    void save(User requestModel);
}
