package interface_adapter.war.war_occur;

import interface_adapter.ViewModel;
import interface_adapter.war.WarGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WarOccurViewModel extends ViewModel {
    public final String IMG_PATH = "img/wartable.png";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public String GO_TO_WAR_LABEL = "go to war";

    public String SURRENDER_LABEL = "surrender";

    public String EXIT_LABEL = "exit";
    private WarGameState state = new WarGameState();

    public WarOccurViewModel() {
        super("war occur");
    }

    public WarGameState getState() {
        return state;
    }

    public void setState(WarGameState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war occur", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
