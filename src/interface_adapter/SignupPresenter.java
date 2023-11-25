package interface_adapter;

import users.signup.SignupOutputBoundary;
import users.signup.SignupOutputData;
import view.LoginState;
import view.LoginViewModel;
import view.SignUpViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState currState = loginViewModel.getState();
        currState.setUsername(response.getUser());
        loginViewModel.setState(currState);

        signUpViewModel.firePropertyChanged();
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(UserCreationFailed error) {
        signUpViewModel.setErrorMessage(error.getMessage());
        signUpViewModel.firePropertyChanged();
    }
}
