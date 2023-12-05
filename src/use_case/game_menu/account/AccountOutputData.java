package use_case.game_menu.account;

/**
 * Represents the output data for account-related operations in the game menu.
 * Contains information such as the username and available funds.
 */
public class AccountOutputData {

    /**
     * The username associated with the account.
     */
    private final String username;

    /**
     * The available funds in the account.
     */
    private final int funds;

    /**
     * Constructs a new AccountOutputData with the specified username and funds.
     *
     * @param username The username associated with the account.
     * @param funds    The available funds in the account.
     */
    public AccountOutputData(String username, int funds) {
        this.username = username;
        this.funds = funds;
    }

    /**
     * Gets the username associated with the account.
     *
     * @return The username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the available funds in the account.
     *
     * @return The available funds.
     */
    public int getFunds() {
        return funds;
    }
}
