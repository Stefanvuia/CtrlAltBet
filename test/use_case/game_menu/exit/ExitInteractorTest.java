package use_case.game_menu.exit;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;

import static org.junit.jupiter.api.Assertions.*;

class ExitInteractorTest {
    private ExitOutputBoundary presenter = new ExitOutputBoundary() {
        @Override
        public void prepareSwapView() {}
    };

    @Test
    void exitTest() {
        ExitInputBoundary interactor = new ExitInteractor(presenter);
        interactor.execute();
    }

}