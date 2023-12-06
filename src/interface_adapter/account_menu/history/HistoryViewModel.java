package interface_adapter.account_menu.history;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HistoryViewModel extends ViewModel {

    public static final String BLACKJACK_BUTTON_LABEL = "blackjack";
    public static final String BACCARAT_BUTTON_LABEL = "baccarat";
    public static final String WAR_BUTTON_LABEl = "war";
    public static final String RESET_GRAPH_LABEL = "reset";
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private HistoryState historyState = new HistoryState();

    public HistoryViewModel() {
        super("history");
    }

    public HistoryState getHistoryState() {
        return historyState;
    }

    public void setHistoryState(HistoryState historyState) {
        this.historyState = historyState;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("history", null, this.historyState);
    }

}
