package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the view model for account information, providing data for the user interface.
 * Extends the abstract class ViewModel and manages the state of the associated AccountInfoState.
 */
public class AccountInfoViewModel extends ViewModel{
    private AccountInfoState accountInfoState = new AccountInfoState();
    public final String USER_LABEL = "current user: ";

    public final String FUNDS_LABEL = "current funds: ";

    public final String DEPOSIT_LABEL = "deposit";

    public final String WITHDRAW_LABEL = "withdraw";

    public final String STATISTICS_LABEL = "statistics";

    public final String SIGN_OUT_LABEL = "sign out";

    public final String EXIT_LABEL = "exit";

    public final String SUCCESS_NOTE = "Successfully edited funds!";

    /**
     * Gets the current state of account information.
     *
     * @return The AccountInfoState object representing the current state.
     */
    public AccountInfoState getAccountInfoState() {
        return accountInfoState;
    }

    /**
     * Sets the current state of account information.
     *
     * @param accountInfoState The new AccountInfoState representing the updated state.
     */
    public void setAccountInfoState(AccountInfoState accountInfoState) {
        this.accountInfoState = accountInfoState;
    }

    /**
     * Constructs a new AccountInfoViewModel with the view name set to "account info."
     */
    public AccountInfoViewModel() {
        super("account info");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered listeners about changes in the account information state.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("account info", null, this.getAccountInfoState());
    }

    /**
     * Adds a property change listener to the support object.
     *
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
