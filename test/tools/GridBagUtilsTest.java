package tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

public class GridBagUtilsTest {

    private GridBagUtils gridBagUtils;

    @Mock
    private Container mockContainer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gridBagUtils = new GridBagUtils(mockContainer);
    }

    @Test
    public void addComponentWithConstraintsTest() {
        Component mockComponent = new Button();

        int gridx = 1, gridy = 2, gridwidth = 3, gridheight = 4;
        double weightx = 0.5, weighty = 0.6;

        gridBagUtils.addComponentWithConstraints(mockComponent, gridx, gridy, gridwidth, gridheight, weightx, weighty);

        ArgumentCaptor<GridBagConstraints> argumentCaptor = ArgumentCaptor.forClass(GridBagConstraints.class);
        verify(mockContainer).add(eq(mockComponent), argumentCaptor.capture());

        GridBagConstraints capturedConstraints = argumentCaptor.getValue();
        assertEquals(gridx, capturedConstraints.gridx);
        assertEquals(gridy, capturedConstraints.gridy);
        assertEquals(gridwidth, capturedConstraints.gridwidth);
        assertEquals(gridheight, capturedConstraints.gridheight);
        assertEquals(weightx, capturedConstraints.weightx);
        assertEquals(weighty, capturedConstraints.weighty);
        assertEquals(GridBagConstraints.BOTH, capturedConstraints.fill);
    }
}
