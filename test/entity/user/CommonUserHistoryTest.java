package entity.user;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommonUserHistoryTest {

    private CommonUserHistory userHistory;

    @Before
    public void setUp() {
        userHistory = new CommonUserHistory("testUser");
    }

    @Test
    public void testGetUsername() {
        // Assert
        assertEquals("testUser", userHistory.getUsername());
    }

    @Test
    public void testGetPayouts() {
        // Arrange
        String game = "blackjack";

        // Act
        ArrayList<Double> payouts = userHistory.getPayouts(game);

        // Assert
        assertNotNull(payouts);
        assertTrue(payouts.isEmpty());
    }

    @Test
    public void testSetPayouts() {
        // Arrange
        String game = "blackjack";
        Double payoutValue = 10.0;

        // Act
        userHistory.setPayouts(game, payoutValue);
        ArrayList<Double> payouts = userHistory.getPayouts(game);

        // Assert
        assertNotNull(payouts);
        assertEquals(1, payouts.size());
        assertEquals(payoutValue, payouts.get(0));
    }

    @Test
    public void testPayoutFormatter() {
        // Arrange
        String game = "blackjack";
        userHistory.setPayouts(game, 10.0);
        userHistory.setPayouts(game, 20.0);

        // Act
        String formattedPayouts = userHistory.payoutFormatter(game);

        // Assert
        assertEquals("10.0 20.0", formattedPayouts);
    }

    @Test
    public void testPayoutFormatterEmpty() {
        // Arrange
        String game = "blackjack";

        // Act
        String formattedPayouts = userHistory.payoutFormatter(game);

        // Assert
        assertEquals("", formattedPayouts);
    }
}