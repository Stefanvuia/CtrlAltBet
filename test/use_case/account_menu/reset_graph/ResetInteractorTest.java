package use_case.account_menu.reset_graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ResetInteractorTest {

    private ResetInteractor resetInteractor;

    @Mock
    private ResetDataAccessInterface historyDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resetInteractor = new ResetInteractor(historyDAO);
    }

    @Test
    void resetGraphTest() throws IOException {
        String username = "Abel";
        ResetInputData inputData = new ResetInputData(username);

        // Act
        resetInteractor.resetGraph(inputData);

        // Assert
        // Verify that the reset method is called on historyDAO with the correct username
        verify(historyDAO).reset(username);
    }

}