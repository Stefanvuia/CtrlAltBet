package interface_adapter.game_menu.launch_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LaunchViewModel extends ViewModel {
    public final String BLACKJACK_LABEL = "blackjack";
    public final String BACCARAT_LABEL = "baccarat";
    public final String WAR_LABEL = "war";
    public final String ACCOUNT_LABEL = "account";
    public final String BLACKJACK_NAME = "bj start";
    public final String BACCARAT_NAME = "baccarat start";
    public final String WAR_NAME = "war start";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private LaunchState state = new LaunchState();

    public LaunchViewModel() {
        super("main menu");
    }

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
