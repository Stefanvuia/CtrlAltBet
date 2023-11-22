package interface_adapter.baccarat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BaccaratStartViewModel extends ViewModel {
    private BaccaratStartState state = new BaccaratStartState();

    public String IMG_PATH = "img/baccarattable.png";

    public String START_LABEL = "start";

    public String INFO_LABEL = "info";

    public String EXIT_LABEL = "exit";

    public String INFO_PATH = "https://github.com/Stefanvuia/CtrlAltBet";

    public String PLAYER_BET = "player";

    public String BANKER_BET = "banker";

    public String TIE_BET = "tie";

    public BaccaratStartViewModel() {
        super("baccarat start");
    }

    public void setState(BaccaratStartState state) {
        this.state = state;
    }

    public BaccaratStartState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("baccarat start", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
