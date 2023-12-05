package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * An abstract class representing a ViewModel in the Model-View-ViewModel (MVVM) architecture.
 * ViewModels encapsulate the data and behavior needed for a specific view in a graphical user interface.
 * This class provides a common structure for implementing ViewModel classes.
 */
public abstract class ViewModel {

    private final String viewName;

    /**
     * Constructs a new ViewModel with the specified view name.
     *
     * @param viewName The name associated with the view.
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the name associated with the view represented by this ViewModel.
     *
     * @return The view name.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Notifies registered listeners about changes in the ViewModel's state.
     * Subclasses must implement this method to provide specific notification logic.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds a property change listener to the ViewModel.
     * Subclasses must implement this method to add listeners based on their specific requirements.
     *
     * @param listener The listener to be added.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}