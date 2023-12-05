package interface_adapter.launch_menu.buttons;

import interface_adapter.ViewManagerModel;
import interface_adapter.launch_menu.login.LoginState;
import interface_adapter.launch_menu.login.LoginViewModel;
import interface_adapter.launch_menu.sign_up.SignUpViewModel;
import interface_adapter.launch_menu.sign_up.SignupState;
import use_case.launch_menu.buttons.UserButtonsOutputBoundary;
import use_case.launch_menu.buttons.UserButtonsOutputData;

/**
 * Presenter class responsible for handling the output and presentation logic
 * related to user button interactions in the launch menu.
 */
public class UserButtonsPresenter implements UserButtonsOutputBoundary {

    /**
     * The model responsible for managing the active view in the application.
     */
    private ViewManagerModel viewManagerModel;

    /**
     * The ViewModel associated with the login functionality.
     */
    private LoginViewModel loginViewModel;

    /**
     * The ViewModel associated with the sign-up functionality.
     */
    private SignUpViewModel signUpViewModel;

    /**
     * Creates a new instance of the UserButtonsPresenter.
     *
     * @param viewManagerModel The model for managing the active view.
     * @param loginViewModel    The ViewModel associated with the login functionality.
     * @param signUpViewModel   The ViewModel associated with the sign-up functionality.
     */
    public UserButtonsPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signUpViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signUpViewModel;
    }

    /**
     * Prepares the UI for swapping to a new view based on the provided output data.
     *
     * @param outputData The output data containing information for the new view.
     */
    @Override
    public void prepareSwap(UserButtonsOutputData outputData) {
        loginViewModel.setState(new LoginState());
        loginViewModel.firePropertyChanged();

        signUpViewModel.setState(new SignupState());
        signUpViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(outputData.getNewView());
        viewManagerModel.firePropertyChanged();
    }
}