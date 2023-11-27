package interface_adapter.history;

import interface_adapter.ViewModel;
import org.knowm.xchart.XYChart;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HistoryViewModel extends ViewModel {

    public static final String BLACKJACK_BUTTON_LABEL = "blackjack";
    public static final String BACCARAT_BUTTON_LABEL = "baccarat";
    public static final String WAR_BUTTON_LABEl = "war";
    public static final String BACK_BUTTON_LABEL = "back";

    private HistoryState historyState = new HistoryState();

    public HistoryViewModel() {
        super("history");
    }

    public void setHistoryState(HistoryState historyState) {
        this.historyState = historyState;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public HistoryState getHistoryState() {
        return historyState;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    // TODO make sure property name and overall implementation is correct
    @Override
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("state", null, this.historyState);
    }

    // Not sure if needed
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
