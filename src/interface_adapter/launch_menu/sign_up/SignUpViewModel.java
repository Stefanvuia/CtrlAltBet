package interface_adapter.launch_menu.sign_up;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class representing the state and behavior of the sign-up view in the launch menu.
 */
public class SignUpViewModel extends ViewModel {

    /**
     * The label for the sign-up button.
     */
    public final String SIGN_UP_LABEL = "sign up";

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
     * The label for the password confirmation field.
     */
    public final String PASSWORD_CONFIRM = "confirm password";
    /**
     * The PropertyChangeSupport instance for notifying listeners about property changes.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * The current state of the sign-up view.
     */
    private SignupState state = new SignupState();

    /**
     * Constructs a new instance of SignUpViewModel with the initial view name "sign up".
     */
    public SignUpViewModel() {
        super("sign up");
    }

    /**
     * Notifies listeners about property changes related to the sign-up view.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("sign up", null, this.state);
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
     * Gets the current state of the sign-up view.
     *
     * @return The current SignupState.
     */
    public SignupState getState() {
        return state;
    }

    /**
     * Sets the state of the sign-up view.
     *
     * @param state The new state to set.
     */
    public void setState(SignupState state) {
        this.state = state;
    }
}