package interface_adapter;

/**
 * Represents the state of account information, including username, funds, change, and error message.
 * This class is used to encapsulate the current state of an account for display and interaction purposes.
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
     * Gets the username associated with the account.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the account.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the funds available in the account.
     *
     * @return The funds.
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Sets the funds available in the account.
     *
     * @param funds The new funds.
     */
    public void setFunds(int funds) {
        this.funds = funds;
    }

    /**
     * Gets the error message associated with the account state.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message associated with the account state.
     *
     * @param errorMessage The new error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
