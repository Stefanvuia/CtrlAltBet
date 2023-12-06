package use_case.games.war.war_start;

import data_access.FileUserDataAccessObject;
import data_access.HistoryDataAccessObject;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.games.CardsAPIInterface;
import use_case.games.war.WarTestAPIObject;
import use_case.games.war.war_started.WarStartInputData;
import use_case.games.war.war_started.WarStartInteractor;
import use_case.games.war.war_started.WarStartOutputBoundary;
import use_case.games.war.war_started.WarStartOutputData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WarStartWarInGameTest {
    private CardsAPIInterface cardDrawer;
    private final FileUserDataAccessObject dao = getDAO();

    private final HistoryDataAccessInterface hDao = getHistoryDAO();

    private final WarStartOutputBoundary warPresenter = new WarStartOutputBoundary() {

        @Override
        public void prepareWarIngameView(WarStartOutputData outputData) {
            ArrayList<Double> array1 = new ArrayList<Double>();
            ArrayList<Double> array2 = new ArrayList<Double>();
            array1.add(0.0);
            array1.add(500.0);
            array2.add(0.0);
            array2.add(-500.0);

            assertNotNull(outputData.getGame());
            assertEquals(500, outputData.getBet());
            assertEquals("cakev", outputData.getUser());
            if(outputData.getGame().playerWins()){
                assertEquals(1500, dao.getFund("cakev"));
                assertEquals(array1 ,hDao.getPayouts("cakev","war"));
            } else{
                assertEquals(500, dao.getFund("cakev"));
                assertEquals(array2 ,hDao.getPayouts("cakev","war"));
            }

        }
        @Override
        public void prepareGoToWarView(WarStartOutputData outputData) {
            fail("Use case success is unexpected.");
        }

        @Override
        public void prepareFailView(String error) {
            fail("Use case success is unexpected.");
        }
    };

    @BeforeEach
    void setUp() {
        User user = new CommonUser("cakev", "qwerty", LocalDateTime.now(), 1000);
        dao.save(user);

    }
    @AfterEach
    void deleteFiles() {
        File file1 = new File("testusers.csv");
        File file2 = new File("./testhistory.csv");
        file1.delete();
        file2.delete();

    }

    @Test
    void WarTestWin() {
        cardDrawer = new WarTestAPIObject(false, true);
        WarStartInputData inputData = new WarStartInputData("cakev", 500);
        WarStartInteractor interactor = new WarStartInteractor(cardDrawer, dao, hDao, warPresenter);
        interactor.execute(inputData);
    }
    @Test
    void WarTestLose() {
        cardDrawer = new WarTestAPIObject(false, false);
        WarStartInputData inputData = new WarStartInputData("cakev", 500);
        WarStartInteractor interactor = new WarStartInteractor(cardDrawer, dao, hDao, warPresenter);
        interactor.execute(inputData);
    }

    private static FileUserDataAccessObject getDAO() {
        FileUserDataAccessObject fileUserDataAccessObject;
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("testusers.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return fileUserDataAccessObject;
    }

    public static HistoryDataAccessObject getHistoryDAO() {
        HistoryDataAccessObject historyDataAccessObject;
        try {
            historyDataAccessObject = new HistoryDataAccessObject("./testhistory.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        return historyDataAccessObject;
    }
}
