package interface_adapter.launch_menu.login;

import entity.user.UserDataAccessFailed;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_menu.launch_game.LaunchState;
import interface_adapter.game_menu.launch_game.LaunchViewModel;
import use_case.launch_menu.login.LoginOutputBoundary;
import use_case.launch_menu.login.LoginOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Presenter class responsible for handling the output and presentation logic
 * related to user login in the launch menu.
 */
public class LoginPresenter implements LoginOutputBoundary {

    /**
     * The ViewModel associated with the login functionality.
     */
    private final LoginViewModel loginViewModel;

    /**
     * The ViewModel associated with the launch game functionality.
     */
    private final LaunchViewModel launchViewModel;

    /**
     * The model for managing the active view in the application.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Creates a new instance of the LoginPresenter.
     *
     * @param loginViewModel    The ViewModel associated with the login functionality.
     * @param launchViewModel   The ViewModel associated with the launch game functionality.
     * @param viewManagerModel  The model for managing the active view.
     */
    public LoginPresenter(LoginViewModel loginViewModel, LaunchViewModel launchViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.launchViewModel = launchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the UI for a successful login by updating the view models and changing the active view.
     *
     * @param response The output data containing information about the successful login.
     */
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        LaunchState currState = launchViewModel.getState();
        currState.setUsername(response.getUser().getName());
        launchViewModel.setState(currState);

        viewManagerModel.setActiveView(launchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the UI for a failed login by updating the login view model and resetting its state.
     *
     * @param error The error information related to the failed login attempt.
     */
    @Override
    public void prepareFailView(UserDataAccessFailed error) {
        LoginState currState = loginViewModel.getState();
        currState.setError(error.getMessage());
        loginViewModel.setState(currState);

        loginViewModel.firePropertyChanged();
        loginViewModel.setState(new LoginState());
    }
}