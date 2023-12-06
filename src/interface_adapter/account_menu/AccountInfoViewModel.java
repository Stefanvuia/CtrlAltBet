package interface_adapter.account_menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the view model for the account information in the user interface.
 * Extends ViewModel and manages the state of the account information.
 */
public class AccountInfoViewModel extends ViewModel {
    public final String USER_LABEL = "current user: ";
    public final String FUNDS_LABEL = "current funds: ";
    public final String DEPOSIT_LABEL = "deposit";
    public final String WITHDRAW_LABEL = "withdraw";
    public final String STATISTICS_LABEL = "statistics: ";
    public final String SIGN_OUT_LABEL = "sign out";
    public final String EXIT_LABEL = "exit";
    public final String BACK_LABEL = "back";
    public final String SUCCESS_NOTE = "Successfully edited funds!";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private AccountInfoState accountInfoState = new AccountInfoState();

    /**
     * Constructs a new AccountInfoViewModel with the specified view name.
     */
    public AccountInfoViewModel() {
        super("account info");
    }

    /**
     * Retrieves the current state of the account information.
     *
     * @return The account information state.
     */
    public AccountInfoState getAccountInfoState() {
        return accountInfoState;
    }

    /**
     * Sets the state of the account information.
     *
     * @param accountInfoState The new account information state.
     */
    public void setAccountInfoState(AccountInfoState accountInfoState) {
        this.accountInfoState = accountInfoState;
    }

    /**
     * Notifies listeners of a property change in the account information.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("account info", null, this.getAccountInfoState());
    }

    /**
     * Adds a property change listener to the account information view model.
     *
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
