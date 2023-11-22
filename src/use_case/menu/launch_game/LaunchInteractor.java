package use_case.menu.launch_game;

import use_case.menu.MenuDataAccessInterface;

public class LaunchInteractor implements LaunchInputBoundary {
    final MenuDataAccessInterface launchDAO;

    final LaunchOutputBoundary launchPresenter;

    public LaunchInteractor(MenuDataAccessInterface launchDAO,
                            LaunchOutputBoundary launchPresenter) {
        this.launchDAO = launchDAO;
        this.launchPresenter = launchPresenter;
    }

    @Override
    public void execute(LaunchInputData inputData) {
        String game = inputData.getGame();
        String username = inputData.getUsername();

        int funds = launchDAO.getFund(username);
        launchPresenter.prepareGameView(new LaunchOutputData(username, game, funds));
    }
}
