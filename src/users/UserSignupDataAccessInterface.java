package users;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    void save(UserSignupDsData requestModel);
}
