package users.update;


import entity.user.User;

// Sign up response body
public class UpdateOutputData {

    private final User user;

    public UpdateOutputData(User user, String loginTime) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
