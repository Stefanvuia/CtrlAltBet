package interface_adapter.account_menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AccountInfoViewModel extends ViewModel {
    private AccountInfoState accountInfoState = new AccountInfoState();
    public final String USER_LABEL = "current user: ";

    public final String FUNDS_LABEL = "current funds: ";

    public final String DEPOSIT_LABEL = "deposit";

    public final String WITHDRAW_LABEL = "withdraw";

    public final String STATISTICS_LABEL = "statistics: ";

    public final String SIGN_OUT_LABEL = "sign out";

    public final String EXIT_LABEL = "exit";

    public final String SUCCESS_NOTE = "Successfully edited funds!";

    public AccountInfoState getAccountInfoState() {
        return accountInfoState;
    }

    public void setAccountInfoState(AccountInfoState accountInfoState) {
        this.accountInfoState = accountInfoState;
    }

    public AccountInfoViewModel() {
        super("account info");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("account info", null, this.getAccountInfoState());
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
