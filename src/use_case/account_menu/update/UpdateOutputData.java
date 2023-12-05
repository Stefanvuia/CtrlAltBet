package use_case.account_menu.update;


import entity.user.User;

/**
 * Represents the response body for a successful user balance update operation.
 */
public class UpdateOutputData {

    /**
     * The new funds after the balance update.
     */
    private final int newFunds;

    /**
     * Constructs a new UpdateOutputData with the specified new funds value.
     *
     * @param newFunds The new funds after the balance update.
     */
    public UpdateOutputData(int newFunds) {
        this.newFunds = newFunds;
    }

    /**
     * Gets the new funds value after the balance update.
     *
     * @return The new funds value.
     */
    public int getFunds() {
        return newFunds;
    }

}
