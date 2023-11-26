package interface_adapter.launch_menu.sign_up;

public class SignupState {
    private String error = null;

    private boolean userCreated = false;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }
}
