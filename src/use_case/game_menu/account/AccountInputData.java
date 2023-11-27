package use_case.game_menu.account;

public class AccountInputData {
    private final String username;

    public AccountInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
