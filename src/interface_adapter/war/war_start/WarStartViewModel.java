package interface_adapter.war.war_start;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WarStartViewModel extends ViewModel {
    public final String BET_LABEL = "start";

    public final String EXIT_LABEL = "exit";

    public final String INFO_LABEL = "info";

    public final String MIN_BET_LABEL = "min: 0";

    public final String MAX_BET_LABEL = "max: ";

    public final String HALF_BET_LABEL = "1/2: ";

    public final String IMG_PATH = "img/wartable.png";

    public final String INFO_PATH = "https://github.com/Stefanvuia/CtrlAltBet";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private WarStartState state = new WarStartState();

    public WarStartViewModel() {
        super("war start");
    }

    public WarStartState getState() {
        return state;
    }

    public void setState(WarStartState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war start", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
