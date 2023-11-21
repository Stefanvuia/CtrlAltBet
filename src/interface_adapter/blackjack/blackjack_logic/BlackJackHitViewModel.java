package interface_adapter.blackjack.blackjack_logic;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlackJackHitViewModel extends ViewModel {
    private GameState state = new GameState();

    public String HIT_LABEL = "hit";

    public BlackJackHitViewModel() {
        super("bj ingame");
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("bj hit", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
