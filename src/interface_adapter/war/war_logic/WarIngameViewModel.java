package interface_adapter.war.war_logic;

import interface_adapter.ViewModel;
import interface_adapter.war.WarGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WarIngameViewModel extends ViewModel {
    private WarGameState state = new WarGameState();
    public final String IMG_PATH = "img/wartable.png";

    public final int CARD_HEIGHT = 157;

    public final int CARD_WIDTH = 108;

    public WarIngameViewModel() {super("war during");}
    public void setState(WarGameState state){ this.state = state;}
    public WarGameState getState(){return this.state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war during", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
