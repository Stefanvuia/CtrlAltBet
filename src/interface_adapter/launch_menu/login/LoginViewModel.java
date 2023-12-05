package interface_adapter.launch_menu.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class representing the state and behavior of the login view in the launch menu.
 */
public class LoginViewModel extends ViewModel {

    /**
     * The current state of the login view.
     */
    private LoginState state = new LoginState();

    /**
     * The label for the login button.
     */
    public final String LOGIN_LABEL = "log in";

    /**
     * The label for the cancel button.
     */
    public final String CANCEL_LABEL = "cancel";

    /**
     * The label for the username field.
     */
    public final String USERNAME_LABEL = "username";

    /**
     * The label for the password field.
     */
    public final String PASSWORD_LABEL = "password";

    /**
     * The PropertyChangeSupport instance for notifying listeners about property changes.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new instance of LoginViewModel with the initial view name "log in".
     */
    public LoginViewModel() {
        super("log in");
    }

    /**
     * Notifies listeners about property changes related to the login view.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("log in", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the support object.
     *
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the login view.
     *
     * @return The current LoginState.
     */
    public LoginState getState() {
        return state;
    }

    /**
     * Sets the state of the login view.
     *
     * @param state The new state to set.
     */
    public void setState(LoginState state) {
        this.state = state;
    }
}