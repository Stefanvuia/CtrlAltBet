package interface_adapter.war.war_occur;

import interface_adapter.ViewModel;
import interface_adapter.blackjack.blackjack_logic.BlackJackGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WarIngameViewModel extends ViewModel {
    private WarGameState state = new WarGameState();

    public final String IMG_PATH = "img/blackjacktable.png";

    public final int CARD_HEIGHT = 157;

    public final int CARD_WIDTH = 108;

    public String GO_TO_WAR_LABEL = "go to war";

    public String SURRENDER_LABEL = "surrender";

    public String EXIT_LABEL = "exit";

    public String CARD_BACK_URL = "https://www.deckofcardsapi.com/static/img/back.png";

    public WarIngameViewModel() {
        super("war ingame");
    }

    public void setState(WarGameState state) {
        this.state = state;
    }

    public WarGameState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war surrender", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
