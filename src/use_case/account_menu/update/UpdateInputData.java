package use_case.account_menu.update;

/**
 * Represents the input data for updating a user's balance.
 */
public class UpdateInputData {

private String username;
private int balance;

    /**
     * Constructs a new instance of UpdateInputData with the specified username and balance.
     *
     * @param username The username of the user to be updated.
     * @param balance  The new balance value to be set.
     */
    public UpdateInputData(String username, int balance) {
        this.username = username;
        this.balance = balance;
    }

    /**
     * Gets the username associated with the update.
     *
     * @return The username of the user to be updated.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the new balance value for the user.
     *
     * @return The new balance value to be set.
     */
    public int getBalance() {
        return balance;
    }
}
