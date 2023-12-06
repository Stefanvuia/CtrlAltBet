package interface_adapter.launch_menu.sign_up;

import entity.user.UserDataAccessFailed;
import interface_adapter.ViewManagerModel;
import interface_adapter.launch_menu.login.LoginState;
import interface_adapter.launch_menu.login.LoginViewModel;
import use_case.launch_menu.signup.SignupOutputBoundary;
import use_case.launch_menu.signup.SignupOutputData;

/**
 * Presenter class responsible for handling the output and presentation logic
 * related to user sign-up in the launch menu.
 */
public class SignupPresenter implements SignupOutputBoundary {

    /**
     * The ViewModel associated with the login functionality.
     */
    private final LoginViewModel loginViewModel;

    /**
     * The ViewModel associated with the sign-up functionality.
     */
    private final SignUpViewModel signUpViewModel;

    /**
     * The model for managing the active view in the application.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Creates a new instance of SignupPresenter.
     *
     * @param loginViewModel  The ViewModel associated with the login functionality.
     * @param signUpViewModel The ViewModel associated with the sign-up functionality.
     * @param viewManagerModel The model for managing the active view.
     */
    public SignupPresenter(LoginViewModel loginViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the UI for a successful sign-up by updating the view models and changing the active view.
     *
     * @param response The output data containing information about the successful sign-up.
     */
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        LoginState currState = loginViewModel.getState();
        SignupState currSignUpState = signUpViewModel.getState();

        currState.setUsername(response.getUser());
        loginViewModel.setState(currState);

        currSignUpState.setUserCreated(true);
        signUpViewModel.setState(currSignUpState);

        signUpViewModel.firePropertyChanged();
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the UI for a failed sign-up by updating the sign-up view model and firing property change events.
     *
     * @param error The error information related to the failed sign-up attempt.
     */
    @Override
    public void prepareFailView(UserDataAccessFailed error) {
        SignupState signupState = signUpViewModel.getState();
        signupState.setError(error.getMessage());
        signUpViewModel.setState(signupState);
        signUpViewModel.firePropertyChanged();
    }
}