package entity.user;

import org.junit.Before;
import org.junit.Test;
import use_case.account_menu.update.UpdateInputData;
import use_case.account_menu.update.UpdateInteractor;
import use_case.account_menu.update.UpdateOutputData;
import use_case.account_menu.update.UpdateUserDataAccessInterface;
import use_case.account_menu.update.UpdateOutputBoundary;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

// Stub implementation for UpdateUserDataAccessInterface
class StubUpdateUserDataAccess implements UpdateUserDataAccessInterface {
    private User savedUser;

    @Override
    public User getUserByName(String username) {
        // Simulate fetching a user from a data source
        return new User() {
            private int balance = 0; // initialize balance to 0
            @Override
            public boolean passwordIsValid() {
                return false;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public LocalDateTime getCreationTime() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void editBalance(int balance) {
                this.balance += balance;
            }
        };
    }

    @Override
    public void save(User user) {
        // Simulate saving a user to a data source
        savedUser = user;
    }

    public User getSavedUser() {
        return savedUser;
    }

    public void setThrowException(boolean b) {
    }
}

// Stub implementation for UpdateOutputBoundary
class StubUpdateOutputBoundary implements UpdateOutputBoundary {
    private UserDataAccessFailed preparedError;
    private UpdateOutputData preparedOutput;

    @Override
    public void prepareFailView(UserDataAccessFailed error) {
        preparedError = error;
    }

    @Override
    public void prepareSuccessView(UpdateOutputData outputData) {
        preparedOutput = outputData;
    }

    public UserDataAccessFailed getPreparedError() {
        return preparedError;
    }

    public UpdateOutputData getPreparedOutput() {
        return preparedOutput;
    }
}

public class UpdateInteractorTest {

    private StubUpdateUserDataAccess stubUserDataAccess;
    private StubUpdateOutputBoundary stubUserPresenter;
    private UpdateInteractor updateInteractor;

    @Before
    public void setUp() {
        stubUserDataAccess = new StubUpdateUserDataAccess();
        stubUserPresenter = new StubUpdateOutputBoundary();
        updateInteractor = new UpdateInteractor(stubUserDataAccess, stubUserPresenter);
    }

    @Test
    public void testSuccessfulUpdate() {
        // Arrange
        UpdateInputData input = new UpdateInputData("testUser", 150);

        // Act
        updateInteractor.updateUser(input);

        // Assert
        assertEquals(150, stubUserPresenter.getPreparedOutput().getFunds());
    }


    @Test
    public void testUpdateUserInsufficientFunds() {
        // Arrange
        UpdateInputData input = new UpdateInputData("testUser", -150);

        // Act
        updateInteractor.updateUser(input);

        // Assert
        assertNotNull(stubUserPresenter.getPreparedError());
        assertEquals("Insufficient funds", stubUserPresenter.getPreparedError().getMessage());
    }


    @Test
    public void testUpdateUserException() {
        // Arrange
        UpdateInputData input = new UpdateInputData("testUser", 50);
        stubUserDataAccess.setThrowException(true);

        // Act and Assert
        try {
            updateInteractor.updateUser(input);
        } catch (UserDataAccessFailed e) {
            // Assert
            assertNotNull(stubUserPresenter.getPreparedError());
            assertEquals("Update user failed", stubUserPresenter.getPreparedError().getMessage());
            assertNull(stubUserDataAccess.getSavedUser()); // No user should be saved in case of an exception
        }
    }

}