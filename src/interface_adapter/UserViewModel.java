package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;

public class UserViewModel {

    public enum LoginState {
        WELCOME,
        SIGNING_UP,
        LOGGING_IN,
        LOGGED_IN,
        ACCOUNT_INFO,
        BALANCE_INFO,
    }

    /** The username of the user who is currently logged in. Null if not logged in. */
    String currentUser = null;

    private LoginState state = LoginState.WELCOME;

    int balance = 0;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        String oldUser = this.currentUser;
        this.currentUser = currentUser;
        support.firePropertyChange("user", oldUser, this.currentUser);
    }

    public void setState(LoginState state) {
        LoginState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        int oldBalance = this.balance;
        this.balance = balance;
        support.firePropertyChange("balance", oldBalance, this.balance);
    }

    public int withdraw(int amount) {
        setBalance(getBalance() - amount);//TODO: Set boundary
        return getBalance();
    }

    public int deposit(int amount) {
        setBalance(getBalance() + amount);//TODO: Set boundary
        return getBalance();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
