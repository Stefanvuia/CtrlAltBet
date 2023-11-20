package interface_adapter.blackjack.blackjack_logic;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlackJackStandViewModel extends ViewModel {
    private BlackJackGameState state = new BlackJackGameState();

    public final String IMG_PATH = "img/blackjacktable.png";

    public String STAND_LABEL = "stand";

    public String EXIT_LABEL = "exit";

    public String CARD_BACK_URL = "https://www.deckofcardsapi.com/static/img/back.png";

    public BlackJackStandViewModel() {
        super("bj ingame");
    }

    public void setState(BlackJackGameState state) {
        this.state = state;
    }

    public BlackJackGameState getState() {
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
