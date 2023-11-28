package interface_adapter.baccarat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BaccaratGameViewModel extends ViewModel {
    private BaccaratGameState state = new BaccaratGameState();
    public String IMG_PATH = "img/baccarattable.png";

    public int CARD_HEIGHT = 157;

    public int CARD_WIDTH = 108;

    public BaccaratGameViewModel() {
        super("baccarat during");
    }

    public void setState(BaccaratGameState state) {
        this.state = state;
    }

    public BaccaratGameState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("baccarat during", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
