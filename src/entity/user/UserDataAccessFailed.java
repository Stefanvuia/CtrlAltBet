package entity.user;

public class UserDataAccessFailed extends RuntimeException {
    public UserDataAccessFailed(String error) {
        super(error);
    }
}
