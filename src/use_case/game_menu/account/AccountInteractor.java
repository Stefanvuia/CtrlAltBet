package use_case.game_menu.account;

import use_case.game_menu.MenuDataAccessInterface;

public class AccountInteractor implements AccountInputBoundary{
    private final AccountOutputBoundary accountPresenter;

    private final MenuDataAccessInterface menuDAO;

    public AccountInteractor(AccountOutputBoundary accountPresenter, MenuDataAccessInterface menuDAO) {
        this.accountPresenter = accountPresenter;
        this.menuDAO = menuDAO;
    }

    @Override
    public void execute(AccountInputData inputData) {
        String username = inputData.getUsername();
        accountPresenter.prepareSwapView(new AccountOutputData(username, menuDAO.getFund(username)));
    }
}
