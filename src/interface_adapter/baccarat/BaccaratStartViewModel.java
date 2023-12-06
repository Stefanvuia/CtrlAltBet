package interface_adapter.baccarat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the view model for the start screen of the Baccarat game, including user interface elements and game state.
 */
public class BaccaratStartViewModel extends ViewModel {
    /**
     * Fires a property change event to notify observers about changes in the Baccarat start screen state.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * The path to the image representing the Baccarat table.
     */
    public String IMG_PATH = "img/baccarattable.png";

    /**
     * The label for the start button in the user interface.
     */
    public String START_LABEL = "start";

    /**
     * The label for the info button in the user interface.
     */
    public String INFO_LABEL = "info";

    /**
     * The label for the exit button in the user interface.
     */
    public String EXIT_LABEL = "exit";

    /**
     * The path to the information URL displayed when clicking the info button.
     */
    public String INFO_PATH = "https://github.com/Stefanvuia/CtrlAltBet";

    /**
     * The key representing the player bet in the betting amounts map.
     */
    public String PLAYER_BET = "player";

    /**
     * The key representing the banker bet in the betting amounts map.
     */
    public String BANKER_BET = "banker";

    /**
     * The key representing the tie bet in the betting amounts map.
     */
    public String TIE_BET = "tie";
    /**
     * The current state of the Baccarat start screen, including user information, betting details, fund, and error messages.
     */
    private BaccaratStartState state = new BaccaratStartState();

    /**
     * Constructs an instance of the BaccaratStartViewModel class.
     */
    public BaccaratStartViewModel() {
        super("baccarat start");
    }

    /**
     * Gets the current state of the Baccarat start screen.
     *
     * @return The current state of the Baccarat start screen.
     */
    public BaccaratStartState getState() {
        return state;
    }

    /**
     * Sets the current state of the Baccarat start screen.
     *
     * @param state The new state of the Baccarat start screen.
     */
    public void setState(BaccaratStartState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("baccarat start", null, this.state);
    }

    /**
     * Adds a property change listener to the Baccarat start screen view model.
     *
     * @param listener The property change listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
