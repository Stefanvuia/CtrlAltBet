package interface_adapter.games.blackjack.blackjack_logic;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlackJackIngameViewModel extends ViewModel {
    public final String IMG_PATH = "img/blackjacktable.png";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public String STAND_LABEL = "stand";

    public String HIT_LABEL = "hit";

    public String EXIT_LABEL = "exit";


    private BlackJackGameState state = new BlackJackGameState();

    public BlackJackIngameViewModel() {
        super("bj ingame");
    }

    public BlackJackGameState getState() {
        return state;
    }

    public void setState(BlackJackGameState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("bj stand", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
