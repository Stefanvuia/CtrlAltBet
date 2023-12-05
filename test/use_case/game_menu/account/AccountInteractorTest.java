package use_case.game_menu.account;

import entity.user.CommonUser;
import org.junit.jupiter.api.Test;
import use_case.InMemoryHistoryDataAccessObject;
import use_case.game_menu.MenuDataAccessInterface;
import use_case.InMemoryUserDataAccessObject;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountInteractorTest {
    private AccountOutputBoundary presenter = new AccountOutputBoundary() {
        @Override
        public void prepareSwapView(AccountOutputData accountOutputData) {
            assertEquals("cakev", accountOutputData.getUsername());
            assertEquals(1000, accountOutputData.getFunds());
        }
    };

    private MenuDataAccessInterface dao = new InMemoryUserDataAccessObject();

    @Test
    void accountTest() {
        ((InMemoryUserDataAccessObject) dao).save(new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000));
        AccountInputBoundary interactor = new AccountInteractor(presenter, dao);
        interactor.execute(new AccountInputData("cakev"));
    }

}