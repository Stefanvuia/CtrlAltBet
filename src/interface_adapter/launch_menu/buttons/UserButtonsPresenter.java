package interface_adapter.launch_menu.buttons;

import interface_adapter.ViewManagerModel;
import interface_adapter.launch_menu.login.LoginState;
import interface_adapter.launch_menu.login.LoginViewModel;
import interface_adapter.launch_menu.sign_up.SignUpViewModel;
import interface_adapter.launch_menu.sign_up.SignupState;
import use_case.launch_menu.buttons.UserButtonsOutputBoundary;
import use_case.launch_menu.buttons.UserButtonsOutputData;

public class UserButtonsPresenter implements UserButtonsOutputBoundary {
    private ViewManagerModel viewManagerModel;

    private LoginViewModel loginViewModel;

    private SignUpViewModel signUpViewModel;

    public UserButtonsPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signUpViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signUpViewModel;
    }

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
