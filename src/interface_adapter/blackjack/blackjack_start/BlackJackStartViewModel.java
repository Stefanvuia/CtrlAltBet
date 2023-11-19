package interface_adapter.blackjack.blackjack_start;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlackJackStartViewModel extends ViewModel {
    public final String BET_LABEL = "start";

    public final String EXIT_LABEL = "exit";

    public final String INFO_LABEL = "info";

    private StartState state = new StartState();

    public BlackJackStartViewModel() {
        super("start blackjack");
    }

    public void setState(StartState state) {
        this.state = state;
    }

    public StartState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("bj start", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
