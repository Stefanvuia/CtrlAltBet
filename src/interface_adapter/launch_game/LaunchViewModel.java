package interface_adapter.launch_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LaunchViewModel extends ViewModel {
    private LaunchState state = new LaunchState();
    public final String BLACKJACK_LABEL = "blackjack";

    public final String BACCARAT_LABEL = "baccarat";

    public final String WAR_LABEL = "war";

    public final String ACCOUNT_LABEL = "account";

    public LaunchViewModel(String viewName) {
        super(viewName);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("launch menu", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LaunchState getState() {
        return state;
    }

    public void setState(LaunchState state) {
        this.state = state;
    }
}
