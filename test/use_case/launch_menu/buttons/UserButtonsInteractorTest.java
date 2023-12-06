package use_case.launch_menu.buttons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserButtonsInteractorTest {
    private final UserButtonsOutputBoundary presenter = new UserButtonsOutputBoundary() {
        @Override
        public void prepareSwap(UserButtonsOutputData outputData) {
            assertEquals("welcome", outputData.getNewView());
        }
    };

    private final UserButtonsInputBoundary interactor = new UserButtonsInteractor(presenter);

    @Test
    void noInputTest() {
        interactor.execute(new UserButtonsInputData());
    }

    @Test
    void inputTest() {
        interactor.execute(new UserButtonsInputData("welcome"));
    }

}