package interface_adapter.launch_menu.login;

import entity.user.UserCreationFailed;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_menu.launch_game.LaunchState;
import interface_adapter.game_menu.launch_game.LaunchViewModel;
import use_case.launch_menu.login.LoginOutputBoundary;
import use_case.launch_menu.login.LoginOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;

    private final LaunchViewModel launchViewModel;

    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(LoginViewModel loginViewModel, LaunchViewModel launchViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.launchViewModel = launchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getLoginTime());
        response.setLoginTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LaunchState currState = launchViewModel.getState();
        currState.setUsername(response.getUser().getName());
        launchViewModel.setState(currState);

        viewManagerModel.setActiveView(launchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        launchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(UserCreationFailed error) {
        LoginState currState = loginViewModel.getState();
        currState.setError(error.getMessage());
        loginViewModel.setState(currState);

        loginViewModel.firePropertyChanged();
        loginViewModel.setState(new LoginState());
    }
}
