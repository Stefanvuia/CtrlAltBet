package entity.user;

import org.junit.Before;
import org.junit.Test;
import use_case.account_menu.history.HistoryDataAccessInterface;
import use_case.launch_menu.signup.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for the SignupInteractor class.
 */
public class SignupInteractorTest {

    private SignupInteractor signupInteractor;
    private FakeSignupUserDataAccess fakeUserDataAccess;
    private FakeSignupOutputBoundary fakeOutputBoundary;
    private FakeUserFactory fakeUserFactory;
    private FakeHistoryDataAccess fakeHistoryDAO;

    @Before
    public void setUp() {
        fakeUserDataAccess = new FakeSignupUserDataAccess();
        fakeOutputBoundary = new FakeSignupOutputBoundary();
        fakeUserFactory = new FakeUserFactory();
        fakeHistoryDAO = new FakeHistoryDataAccess();

        signupInteractor = new SignupInteractor(fakeUserDataAccess, fakeOutputBoundary, fakeUserFactory, fakeHistoryDAO);
    }

    /**
     * Test for creating a user successfully.
     */
    @Test
    public void testCreateUser_Success() {
        // Arrange
        fakeUserDataAccess.userExists = false;
        SignupInputData inputData = new SignupInputData("testUser", "password123", "password123");
        LocalDateTime now = LocalDateTime.now();

        // Act
        signupInteractor.createUser(inputData);

        // Assert
        assertNull(fakeOutputBoundary.failureData);
        assertNotNull(fakeOutputBoundary.successData);
        assertEquals("testUser", fakeOutputBoundary.successData.getUser());
        assertEquals("testUser", fakeHistoryDAO.addedUser);
    }

    /**
     * Test for creating a user when the user already exists.
     */
    @Test
    public void testCreateUser_UserAlreadyExists() {
        // Arrange
        fakeUserDataAccess.userExists = true;
        SignupInputData inputData = new SignupInputData("existingUser", "password123", "password123");

        // Act
        signupInteractor.createUser(inputData);

        // Assert
        assertNull(fakeOutputBoundary.successData);
        assertNotNull(fakeOutputBoundary.failureData);
        assertEquals("User already exists", fakeOutputBoundary.failureData.getMessage());
        assertNull(fakeHistoryDAO.addedUser);
    }

    /**
     * Test for creating a user when passwords do not match.
     */
    @Test
    public void testCreateUser_PasswordsDoNotMatch() {
        // Arrange
        fakeUserDataAccess.userExists = false;
        SignupInputData inputData = new SignupInputData("testUser", "password123", "mismatchedPassword");

        // Act
        signupInteractor.createUser(inputData);

        // Assert
        assertNull(fakeOutputBoundary.successData);
        assertNotNull(fakeOutputBoundary.failureData);
        assertEquals("Passwords do not match", fakeOutputBoundary.failureData.getMessage());
        assertNull(fakeHistoryDAO.addedUser);
    }

    /**
     * Test for creating a user with an invalid password.
     */
    @Test
    public void testCreateUser_InvalidPassword() {
        // Arrange
        fakeUserDataAccess.userExists = false;
        SignupInputData inputData = new SignupInputData("testUser", "short", "short");

        // Act
        signupInteractor.createUser(inputData);

        // Assert
        assertNull(fakeOutputBoundary.successData);
        assertNotNull(fakeOutputBoundary.failureData);
        assertEquals("User password must have more than 5 characters", fakeOutputBoundary.failureData.getMessage());
        assertNull(fakeHistoryDAO.addedUser);
    }

    /**
     * Fake implementation of SignupUserDataAccessInterface for testing purposes.
     */
    private class FakeSignupUserDataAccess implements SignupUserDataAccessInterface {
        private boolean userExists;

        @Override
        public boolean existsByName(String identifier) {
            return userExists;
        }

        @Override
        public void save(User requestModel) {
            // Implement save method if needed for testing
        }
    }

    /**
     * Fake implementation of SignupOutputBoundary for testing purposes.
     */
    private class FakeSignupOutputBoundary implements SignupOutputBoundary {
        private SignupOutputData successData;
        private UserDataAccessFailed failureData;

        @Override
        public void prepareSuccessView(SignupOutputData user) {
            this.successData = user;
        }

        @Override
        public void prepareFailView(UserDataAccessFailed error) {
            this.failureData = error;
        }
    }

    /**
     * Fake implementation of UserFactory for testing purposes.
     */
    private class FakeUserFactory implements UserFactory {
        @Override
        public User create(String name, String password, LocalDateTime creationTime, int balance) {
            return new CommonUser(name, password, creationTime, balance);
        }
    }

    /**
     * Fake implementation of HistoryDataAccessInterface for testing purposes.
     */
    private class FakeHistoryDataAccess implements HistoryDataAccessInterface {
        private String addedUser;

        @Override
        public void addPayout(String username, String game, double amount) {
            // Implement addPayout method if needed for testing
        }

        @Override
        public ArrayList<Double> getPayouts(String username, String game) {
            return null;
        }

        @Override
        public boolean existsByName(String identifier) {
            return false;
        }

        @Override
        public void addUser(String username) {
            this.addedUser = username;
        }
    }
}