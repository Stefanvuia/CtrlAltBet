package interface_adapter.account_menu;

/**
 * Represents the state of the account information in the user interface.
 * Encapsulates information such as username, funds, change, and error messages.
 */
public class AccountInfoState {
    private String username = "";

    private int funds = 0;

    public int getChange() {
        return change;
    }

    private int change = 0;

    private String errorMessage = null;

    public void setChange(int change) {
        this.change = change;
    }

    /**
     * Constructs a new AccountInfoState with default values.
     */
    public AccountInfoState() {}

    /**
     * Retrieves the username associated with the account.
     *
     * @return The username of the account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the account.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the current funds in the account.
     *
     * @return The funds in the account.
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Sets the funds for the account.
     *
     * @param funds The new funds to be set.
     */
    public void setFunds(int funds) {
        this.funds = funds;
    }

    /**
     * Retrieves the error message associated with the account information state.
     *
     * @return The error message, or null if no error.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message for the account information state.
     *
     * @param errorMessage The error message to be set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
