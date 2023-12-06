package view;


import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Manages the switching of views in a graphical user interface using a CardLayout.
 * Implements PropertyChangeListener to react to changes in the associated ViewManagerModel.
 */
public class ViewManager implements PropertyChangeListener {

    private final CardLayout cardLayout;
    private final JPanel views;

    private final ViewManagerModel viewManagerModel;
//    private AccountInfoViewModel userViewModel = null;

    /**
     * Constructs a new ViewManager with the specified parameters.
     *
     * @param views The JPanel containing the views to be managed.
     * @param cardLayout The CardLayout used for switching between views.
     * @param viewManagerModel The model representing the state of the view manager.
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
//        userViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);

    }

    /**
     * Responds to property change events in the associated ViewManagerModel.
     *
     * This method is called when the associated ViewManagerModel undergoes a property change.
     * It updates the displayed view based on the change in the model's state.
     *
     * @param evt The PropertyChangeEvent representing the change in the model's properties.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }

//        if (evt.getPropertyName().equals("state")) {
//            AccountInfoViewModel.LoginState newValue = (AccountInfoViewModel.LoginState) evt.getNewValue();
//            switch (newValue) {
//                case WELCOME -> cardLayout.show(views, WELCOME);
//                case SIGNING_UP -> cardLayout.show(views, SIGN_UP);
//                case LOGGING_IN -> cardLayout.show(views, LOG_IN);
//                case LOGGED_IN -> cardLayout.show(views, LOGGED_IN);
//                case ACCOUNT_INFO -> cardLayout.show(views, ACCOUNT_INFO);
//                case BALANCE_INFO -> cardLayout.show(views, BALANCE_INFO);
//                default -> throw new IllegalStateException("Unexpected view state: " + evt.getNewValue());
//            }
//        }
    }
}
