package users;

public class SignupOutputData {

    private final String user;
    private String creationTime;

    public SignupOutputData(String user, String creationTime) {
        this.user = user;
        this.creationTime = creationTime;
    }

    public String getUser() {
        return user;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
