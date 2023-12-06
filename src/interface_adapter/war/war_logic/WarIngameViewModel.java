package interface_adapter.war.war_logic;

import interface_adapter.ViewModel;
import interface_adapter.war.WarGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WarIngameViewModel extends ViewModel {
    public final String IMG_PATH = "img/wartable.png";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private WarGameState state = new WarGameState();

    public WarIngameViewModel() {
        super("war during");
    }

    public WarGameState getState() {
        return this.state;
    }

    public void setState(WarGameState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war during", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
