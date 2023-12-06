package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents a model that manages the active view within a graphical user interface.
 * This class utilizes the observer pattern to notify registered listeners about changes
 * in the active view name.
 */
public class ViewManagerModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String activeViewName;

    /**
     * Sets the name of the currently active view.
     *
     * @param activeView The name of the view to be set as active.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Notifies registered listeners about changes in the active view name.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    /**
     * Adds a property change listener to the support object.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
