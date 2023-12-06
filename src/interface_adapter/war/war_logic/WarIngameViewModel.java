package interface_adapter.war.war_logic;

import interface_adapter.ViewModel;
import interface_adapter.war.WarGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The WarIngameViewModel class represents the ViewModel for managing the state and properties
 * related to the in-game view of the War card game.
 *
 * This class extends the Android Architecture Components ViewModel class and includes methods
 * for setting and retrieving the game state, as well as handling property change events.
 */
public class WarIngameViewModel extends ViewModel {

    /** The initial game state for the War card game. */
    private WarGameState state = new WarGameState();

    /** The default image path for the war table background. */
    public final String IMG_PATH = "img/wartable.png";

    /** The height of the cards displayed in the game. */
    public final int CARD_HEIGHT = 157;

    /** The width of the cards displayed in the game. */
    public final int CARD_WIDTH = 108;

    /**
     * Constructs a new WarIngameViewModel with the default title "war during."
     */
    public WarIngameViewModel() {super("war during");}

    /**
     * Sets the current game state to the specified WarGameState.
     *
     * @param state The new game state to set.
     */
    public void setState(WarGameState state){ this.state = state;}

    /**
     * Retrieves the current game state.
     *
     * @return The current WarGameState.
     */
    public WarGameState getState(){return this.state;}

    /**
     * The PropertyChangeSupport instance responsible for managing property change listeners.
     * It is initialized with the current instance of WarIngameViewModel.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies listeners that a property change has occurred, specifically related to the
     * "war during" property.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war during", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to listen for property change events.
     *
     * @param listener The PropertyChangeListener to add.
     */@Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
