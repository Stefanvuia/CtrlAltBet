package interface_adapter.war.war_occur;

import interface_adapter.ViewModel;
import interface_adapter.war.WarGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The WarOccurViewModel class represents the ViewModel for managing the state and properties
 * related when a war occurs in the War card game. It extends the ViewModel class and includes
 * methods for setting and retrieving the game state, as well as handling property change events.
 */
public class WarOccurViewModel extends ViewModel {

    /**
     * The default image path for the war table background.
     */
    public final String IMG_PATH = "img/wartable.png";
    /**
     * The PropertyChangeSupport instance responsible for managing property change listeners.
     * It is initialized with the current instance of WarOccurViewModel.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public String GO_TO_WAR_LABEL = "go to war";

    /**
     * The label for the "Surrender" action button.
     */
    public String SURRENDER_LABEL = "surrender";

    /**
     * The label for the "Exit" action button.
     */
    public String EXIT_LABEL = "exit";
    /**
     * The initial game state for the War card game.
     */
    private WarGameState state = new WarGameState();

    public WarOccurViewModel() {
        super("war occur");
    }

    /**
     * Retrieves the current game state.
     *
     * @return The current WarGameState.
     */
    public WarGameState getState() {
        return state;
    }

    /**
     * Sets the current game state to the specified WarGameState.
     *
     * @param state The new game state to set.
     */
    public void setState(WarGameState state) {
        this.state = state;
    }

    /**
     * Notifies listeners that a property change has occurred, specifically related to the
     * "war occur" property.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war occur", null, this.state);
    }


    /**
     * Adds a PropertyChangeListener to listen for property change events.
     *
     * @param listener The PropertyChangeListener to add.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
