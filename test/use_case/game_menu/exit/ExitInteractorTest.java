package use_case.game_menu.exit;

import org.junit.jupiter.api.Test;

class ExitInteractorTest {
    private final ExitOutputBoundary presenter = () -> {
    };

    @Test
    void exitTest() {
        ExitInputBoundary interactor = new ExitInteractor(presenter);
        interactor.execute();
    }

}