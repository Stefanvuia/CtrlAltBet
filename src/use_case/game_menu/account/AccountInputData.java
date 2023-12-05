package use_case.game_menu.account;

/**
 * Represents input data for account-related operations in the game menu.
 */
public class AccountInputData {

    /**
     * The username associated with the account.
     */
    private final String username;

    /**
     * Constructs a new AccountInputData with the specified username.
     *
     * @param username The username associated with the account.
     */
    public AccountInputData(String username) {
        this.username = username;
    }

    /**
     * Gets the username associated with the account.
     *
     * @return The username.
     */
    public String getUsername() {
        return this.username;
    }
}
