package interface_adapter.account_menu.sign_out;

import interface_adapter.ViewManagerModel;
import interface_adapter.launch_menu.WelcomeViewModel;
import use_case.account_menu.sign_out.SignOutOutputBoundary;

/**
 * Presenter class responsible for preparing the welcome view upon successful sign-out.
 * Implements the SignOutOutputBoundary to handle the interaction between the sign-out use case
 * and the user interface.
 */
public class SignOutPresenter implements SignOutOutputBoundary {
    private WelcomeViewModel welcomeViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new SignOutPresenter with the specified WelcomeViewModel and ViewManagerModel.
     *
     * @param welcomeViewModel The view model for the welcome view.
     * @param viewManagerModel The model managing the active view in the view manager.
     */
    public SignOutPresenter(WelcomeViewModel welcomeViewModel, ViewManagerModel viewManagerModel) {
        this.welcomeViewModel = welcomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares and triggers the display of the welcome view after a successful sign-out.
     * Invokes the necessary changes in the ViewManagerModel to update the active view.
     */
    @Override
    public void prepareWelcomeView() {
        viewManagerModel.setActiveView(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
