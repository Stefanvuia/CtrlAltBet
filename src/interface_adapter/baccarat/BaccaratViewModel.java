package interface_adapter.baccarat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BaccaratViewModel extends ViewModel {
    private BaccaratState state = new BaccaratState();

    public String IMG_PATH = "img/baccarattable.png";

    public String START_LABEL = "start";

    public String INFO_LABEL = "info";

    public String EXIT_LABEL = "exit";

    private String secondaryViewName = "baccarat during";

    public String CARD_BACK_URL = "https://www.deckofcardsapi.com/static/img/back.png";

    public BaccaratViewModel() {
        super("baccarat start");
    }

    public void setState(BaccaratState state) {
        this.state = state;
    }

    public BaccaratState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("baccarat", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getSecondaryViewName() {
        return secondaryViewName;
    }
}
