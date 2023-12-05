package interface_adapter.account_menu.history;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HistoryViewModel extends ViewModel {

    public static final String BLACKJACK_BUTTON_LABEL = "blackjack";
    public static final String BACCARAT_BUTTON_LABEL = "baccarat";
    public static final String WAR_BUTTON_LABEl = "war";
    public static final String RESET_GRAPH_LABEL = "reset graphs";

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

    @Override
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("history", null, this.historyState);
    }

}
