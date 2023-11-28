package interface_adapter.launch_menu.sign_up;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpViewModel extends ViewModel {
    public final String SIGN_UP_LABEL = "sign up";
    public final String CANCEL_LABEL = "cancel";

    public final String USERNAME_LABEL = "username";

    public final String PASSWORD_LABEL = "password";
    public final String PASSWORD_CONFIRM = "confirm password";


    private SignupState state = new SignupState();

    public SignUpViewModel() {
        super("sign up");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("sign up", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }

    public void setState(SignupState state) {
        this.state = state;
    }
}
