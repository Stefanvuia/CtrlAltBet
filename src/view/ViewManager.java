package view;


import interface_adapter.UserViewModel;
import interface_adapter.ViewManagerModel;

//import interface_adapter.UserViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static interface_adapter.UserViewModel.LoginState.WELCOME;

public class ViewManager implements PropertyChangeListener {

    public static final String WELCOME = "welcome";
    public static final String SIGN_UP = "sign up";
    public static final String LOG_IN = "log in";
    public static final String LOGGED_IN = "main menu";

    public static final String ACCOUNT_INFO = "account info";
    public static final String BALANCE_INFO = "balance info";
    private final CardLayout cardLayout;
    private final JPanel views;

    private ViewManagerModel viewManagerModel;
    private UserViewModel userViewModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel, UserViewModel userViewModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        userViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }

        if (evt.getPropertyName().equals("state")) {
            UserViewModel.LoginState newValue = (UserViewModel.LoginState) evt.getNewValue();
            switch (newValue) {
                case WELCOME -> cardLayout.show(views, WELCOME);
                case SIGNING_UP -> cardLayout.show(views, SIGN_UP);
                case LOGGING_IN -> cardLayout.show(views, LOG_IN);
                case LOGGED_IN -> cardLayout.show(views, LOGGED_IN);
                case ACCOUNT_INFO -> cardLayout.show(views, ACCOUNT_INFO);
                case BALANCE_INFO -> cardLayout.show(views, BALANCE_INFO);
                default -> throw new IllegalStateException("Unexpected view state: " + evt.getNewValue());
            }
        }
    }
}
