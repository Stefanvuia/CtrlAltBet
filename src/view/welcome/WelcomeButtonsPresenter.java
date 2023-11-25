package view.welcome;

import interface_adapter.ViewManagerModel;

public class WelcomeButtonsPresenter implements WelcomeButtonsOutputBoundary{
    private ViewManagerModel viewManagerModel;

    public WelcomeButtonsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSwap(WelcomeButtonsOutputData outputData) {
        viewManagerModel.setActiveView(outputData.getNewView());
        viewManagerModel.firePropertyChanged();
    }
}
