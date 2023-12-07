package interface_adapter.games.war.war_start;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The WarStartViewModel class represents the ViewModel for managing the state and properties
 * related to the start phase of the War card game. It extends the ViewModel class and includes
 * methods for setting and retrieving the game state, as well as handling property change events.
 */
public class WarStartViewModel extends ViewModel {

    /**
     * The label for the "Start" button.
     */
    public final String BET_LABEL = "start";

    /**
     * The label for the "Exit" button.
     */
    public final String EXIT_LABEL = "exit";

    /**
     * The label for the "Info" button.
     */
    public final String INFO_LABEL = "info";

    /**
     * The label indicating the minimum bet amount.
     */
    public final String MIN_BET_LABEL = "min: 0";

    /**
     * The label indicating the maximum bet amount.
     */
    public final String MAX_BET_LABEL = "max: ";

    /**
     * The label indicating half of the bet amount.
     */
    public final String HALF_BET_LABEL = "1/2: ";

    /**
     * The default image path for the war table background.
     */
    public final String IMG_PATH = "img/wartable.png";

    /**
     * The URL for additional information about the game.
     */
    public final String INFO_PATH = "https://github.com/Stefanvuia/CtrlAltBet#war";
    /**
     * The PropertyChangeSupport instance responsible for managing property change listeners.
     * It is initialized with the current instance of WarStartViewModel.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * The initial game state for the War card game during the start phase.
     */
    private WarStartState state = new WarStartState();

    /**
     * Constructs a new WarStartViewModel with the default title "war start."
     */
    public WarStartViewModel() {
        super("war start");
    }

    /**
     * Retrieves the current game state.
     *
     * @return The current WarStartState.
     */
    public WarStartState getState() {
        return state;
    }

    /**
     * Sets the current game state to the specified WarStartState.
     *
     * @param state The new game state to set.
     */
    public void setState(WarStartState state) {
        this.state = state;
    }

    /**
     * Notifies listeners that a property change has occurred, specifically related to the
     * "war start" property.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("war start", null, this.state);
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
