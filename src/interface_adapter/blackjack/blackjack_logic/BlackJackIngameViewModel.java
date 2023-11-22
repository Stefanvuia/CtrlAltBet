package interface_adapter.blackjack.blackjack_logic;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlackJackIngameViewModel extends ViewModel {
    private BlackJackGameState state = new BlackJackGameState();

    public final String IMG_PATH = "img/blackjacktable.png";

    public final int CARD_HEIGHT = 157;

    public final int CARD_WIDTH = 108;

    public String STAND_LABEL = "stand";

    public String HIT_LABEL = "hit";

    public String EXIT_LABEL = "exit";

    public String CARD_BACK_URL = "https://www.deckofcardsapi.com/static/img/back.png";

    public BlackJackIngameViewModel() {
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
