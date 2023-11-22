package use_case.launch_game;

import use_case.GameDataAccessInterface;

public class LaunchInteractor implements LaunchInputBoundary {
    final LaunchDataAccessInterface launchDAO;

    final LaunchOutputBoundary launchPresenter;

    public LaunchInteractor(LaunchDataAccessInterface launchDAO,
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
