package interface_adapter.blackjack.blackjack_logic;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlackJackStandViewModel extends ViewModel {
    private GameState state = new GameState();

    public String STAND_LABEL = "stand";

    public String EXIT_LABEL = "exit";

    public String CARD_BACK_URL = "https://www.deckofcardsapi.com/static/img/back.png";

    public BlackJackStandViewModel() {
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
        support.firePropertyChange("bj stand", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
