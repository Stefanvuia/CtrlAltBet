package interface_adapter.account_menu.sign_out;

import interface_adapter.ViewManagerModel;
import interface_adapter.launch_menu.WelcomeViewModel;
import use_case.account_menu.sign_out.SignOutOutputBoundary;

public class SignOutPresenter implements SignOutOutputBoundary {
    private WelcomeViewModel welcomeViewModel;
    private ViewManagerModel viewManagerModel;

    public SignOutPresenter(WelcomeViewModel welcomeViewModel, ViewManagerModel viewManagerModel) {
        this.welcomeViewModel = welcomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareWelcomeView() {
        viewManagerModel.setActiveView(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
