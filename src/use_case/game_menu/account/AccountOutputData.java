package use_case.game_menu.account;

public class AccountOutputData {
    private final String username;

    private final int funds;

    public AccountOutputData(String username, int funds) {
        this.username = username;
        this.funds = funds;
    }

    public String getUsername() {
        return this.username;
    }

    public int getFunds() {
        return funds;
    }
}
