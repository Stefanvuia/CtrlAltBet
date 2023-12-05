package interface_adapter.baccarat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class representing the state of a Baccarat game for use in the user interface.
 */
public class BaccaratGameViewModel extends ViewModel {

    /**
     * The current state of the Baccarat game.
     */
    private BaccaratGameState state = new BaccaratGameState();

    /**
     * The path to the image representing the Baccarat table.
     */
    public String IMG_PATH = "img/baccarattable.png";

    /**
     * Constructs a BaccaratGameViewModel with a default name.
     */
    public BaccaratGameViewModel() {
        super("baccarat during");
    }

    /**
     * Sets the state of the Baccarat game.
     *
     * @param state The new state of the Baccarat game.
     */
    public void setState(BaccaratGameState state) {
        this.state = state;
    }

    /**
     * Gets the current state of the Baccarat game.
     *
     * @return The current state of the Baccarat game.
     */
    public BaccaratGameState getState() {
        return state;
    }

    /**
     * PropertyChangeSupport instance for managing property change listeners.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event for the "baccarat during" property.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("baccarat during", null, this.state);
    }

    /**
     * Adds a property change listener to this ViewModel.
     *
     * @param listener The property change listener to add.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
