package use_case.game_menu.launch_game;

import use_case.game_menu.MenuDataAccessInterface;

/**
 * Interactor class for launching a game from the game menu.
 */
public class LaunchInteractor implements LaunchInputBoundary {

    /**
     * Data access interface for accessing menu-related data.
     */
    final MenuDataAccessInterface launchDAO;

    /**
     * Presenter responsible for displaying the game launch view.
     */
    final LaunchOutputBoundary launchPresenter;

    /**
     * Constructs a LaunchInteractor object with the specified dependencies.
     *
     * @param launchDAO         Data access interface for accessing menu-related data.
     * @param launchPresenter   Presenter for displaying the game launch view.
     */
    public LaunchInteractor(MenuDataAccessInterface launchDAO,
                            LaunchOutputBoundary launchPresenter) {
        this.launchDAO = launchDAO;
        this.launchPresenter = launchPresenter;
    }

    /**
     * Executes the process of launching a game based on the provided input data.
     *
     * @param inputData The input data containing the selected game and player's username.
     */
    @Override
    public void execute(LaunchInputData inputData) {
        String game = inputData.getGame();
        String username = inputData.getUsername();

        int funds = launchDAO.getFund(username);
        launchPresenter.prepareGameView(new LaunchOutputData(username, game, funds));
    }
}
