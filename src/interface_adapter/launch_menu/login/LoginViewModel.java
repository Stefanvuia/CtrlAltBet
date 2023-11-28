package interface_adapter.launch_menu.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    private LoginState state = new LoginState();

    public final String LOGIN_LABEL = "log in";

    public final String CANCEL_LABEL = "cancel";

    public final String USERNAME_LABEL = "username";

    public final String PASSWORD_LABEL = "password";

    public LoginViewModel() {
        super("log in");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("log in", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginState getState() {
        return state;
    }

    public void setState(LoginState state) {
        this.state = state;
    }
}
