package use_case.game_menu.launch_game;

import entity.user.CommonUser;
import org.junit.jupiter.api.Test;
import use_case.InMemoryUserDataAccessObject;
import use_case.game_menu.MenuDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LaunchInteractorTest {
    private MenuDataAccessInterface dao = new InMemoryUserDataAccessObject();
    private LaunchOutputBoundary presenter = new LaunchOutputBoundary() {
        @Override
        public void prepareGameView(LaunchOutputData outputData) {
            assertEquals("blackjack", outputData.getGame());
            assertEquals("cakev", outputData.getUsername());
            assertEquals(1000, outputData.getFunds());
        }
    };

    @Test
    void launchTest() {
        ((InMemoryUserDataAccessObject) dao).save(new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000));
        LaunchInputBoundary interactor = new LaunchInteractor(dao, presenter);
        interactor.execute(new LaunchInputData("cakev", "blackjack"));
    }

}