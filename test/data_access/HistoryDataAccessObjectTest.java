package data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HistoryDataAccessObjectTest {

    private HistoryDataAccessObject historyDAO;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // Use a temporary file for testing
        File tempFile = tempDir.resolve("test.csv").toFile();
        historyDAO = new HistoryDataAccessObject(tempFile.getAbsolutePath());
    }


    @Test
    void initializeEmptyFileTest() throws IOException {
        File tempFile = tempDir.resolve("emptyTest.csv").toFile();
        assertTrue(tempFile.createNewFile());

        HistoryDataAccessObject dao = new HistoryDataAccessObject(tempFile.getAbsolutePath());

        assertFalse(dao.existsByName("anyUser"));
    }

    @Test
    void initializePrePopulatedFileTest() throws IOException {
        File tempFile = tempDir.resolve("prePopulatedTest.csv").toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("username,blackjack,baccarat,war");
            writer.newLine();
            writer.write("john,100.0 -50.0,150.0 -30.0,200.0 -100.0");
            writer.newLine();
        }

        HistoryDataAccessObject dao = new HistoryDataAccessObject(tempFile.getAbsolutePath());

        assertTrue(dao.existsByName("john"));
        assertEquals(Arrays.asList(100.0, -50.0), dao.getPayouts("john", "blackjack"));
        assertEquals(Arrays.asList(150.0, -30.0), dao.getPayouts("john", "baccarat"));
        assertEquals(Arrays.asList(200.0, -100.0), dao.getPayouts("john", "war"));
    }

    @Test
    void addNewUserTest() {
        historyDAO.addUser("Alice");

        assertTrue(historyDAO.existsByName("Alice"));
    }

    @Test
    void addPayoutTest() {
        historyDAO.addPayout("Bob", "blackjack", 100.0);

        assertTrue(historyDAO.existsByName("Bob"));

        ArrayList<Double> payouts = historyDAO.getPayouts("Bob", "blackjack");
        assertEquals(100.0, payouts.get(payouts.size() - 1));

    }

    @Test
    void addPayoutsExistingUserTest() {
        historyDAO.addUser("Carol");
        historyDAO.addPayout("Carol", "blackjack", 50.0);

        ArrayList<Double> payouts = historyDAO.getPayouts("Carol", "blackjack");
        assertNotNull(payouts);
        assertFalse(payouts.isEmpty());
        assertEquals(50.0, payouts.get(payouts.size() - 1));

        historyDAO.addPayout("Carol", "blackjack", 100.0);
        assertEquals(100.0, payouts.get(payouts.size() - 1));
    }

    @Test
    void defaultValueStartTest() {
        historyDAO.addUser("Kevin");

        ArrayList<Double> payoutBJ = historyDAO.getPayouts("Kevin", "blackjack");
        ArrayList<Double> payoutBA = historyDAO.getPayouts("Kevin", "baccarat");
        ArrayList<Double> payoutWAR = historyDAO.getPayouts("Kevin", "war");

        assertEquals(0.0, payoutBJ.get(0));
        assertEquals(0.0, payoutBA.get(0));
        assertEquals(0.0, payoutWAR.get(0));
    }

    @Test
    void resetExistingUserTest() throws IOException {
        historyDAO.addUser("Dave");
        historyDAO.reset("Dave");

        assertFalse(historyDAO.existsByName("Dave"));
    }
}