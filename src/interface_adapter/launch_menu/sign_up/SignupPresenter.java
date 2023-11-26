package interface_adapter.launch_menu.sign_up;

import entity.user.UserCreationFailed;
import interface_adapter.ViewManagerModel;
import use_case.launch_menu.signup.SignupOutputBoundary;
import use_case.launch_menu.signup.SignupOutputData;
import interface_adapter.launch_menu.login.LoginState;
import interface_adapter.launch_menu.login.LoginViewModel;

public class SignupPresenter implements SignupOutputBoundary {

    private final LoginViewModel loginViewModel;

    private final SignUpViewModel signUpViewModel;

    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(LoginViewModel loginViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }

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

    @Override
    public void prepareFailView(UserCreationFailed error) {
        SignupState signupState = signUpViewModel.getState();
        signupState.setError(error.getMessage());
        signUpViewModel.setState(signupState);
        signUpViewModel.firePropertyChanged();
    }
}
