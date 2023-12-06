package entity.user;

import org.junit.jupiter.api.Test;
import use_case.launch_menu.login.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginInteractor class.
 */
public class LoginInteractorTest {

    /**
     * Test for successful user login.
     */
    @Test
    public void testLoginUserSuccess() {
        // Arrange
        LoginUserDataAccessInterface dataAccess = new TestLoginUserDataAccess(true, true);
        TestLoginOutputBoundary presenter = new TestLoginOutputBoundary();

        LoginInteractor interactor = new LoginInteractor(dataAccess, presenter);

        LoginInputData inputData = new LoginInputData("validUser", "validPassword");

        // Act
        interactor.loginUser(inputData);

        // Assert
        assertTrue(presenter.isSuccessViewCalled());
    }

    /**
     * Test for attempting to log in with a nonexistent user.
     */

    @Test
    public void testLoginUserUserNotExist() {
        // Arrange
        LoginUserDataAccessInterface dataAccess = new TestLoginUserDataAccess(false, false);
        TestLoginOutputBoundary presenter = new TestLoginOutputBoundary();

        LoginInteractor interactor = new LoginInteractor(dataAccess, presenter);

        LoginInputData inputData = new LoginInputData("nonexistentUser", "password");

        // Act
        interactor.loginUser(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertEquals("User does not exist", presenter.getErrorMessage());
    }

    /**
     * Test for attempting to login with an incorrect password.
     */

    @Test
    public void testLoginUserIncorrectPassword() {
        // Arrange
        LoginUserDataAccessInterface dataAccess = new TestLoginUserDataAccess(true, false);
        TestLoginOutputBoundary presenter = new TestLoginOutputBoundary();

        LoginInteractor interactor = new LoginInteractor(dataAccess, presenter);

        LoginInputData inputData = new LoginInputData("validUser", "invalidPassword");

        // Act
        interactor.loginUser(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertEquals("User password is Incorrect. Please try again", presenter.getErrorMessage());
    }

    /**
     * Test for creating a common user using the CommonUserFactory.
     */

    @Test
    public void testCreateCommonUser() {
        // Arrange
        UserFactory userFactory = new CommonUserFactory();

        // Act
        User user = userFactory.create("testUser", "testPassword", LocalDateTime.now(), 0);

        // Assert
        assertNotNull(user);
        assertEquals("testUser", user.getName());
        assertEquals("testPassword", user.getPassword());
        assertEquals(0, user.getBalance());
    }
}

/**
 * Fake implementation of LoginUserDataAccessInterface for testing purposes.
 */
class TestLoginUserDataAccess implements LoginUserDataAccessInterface {

    private final boolean userExists;
    private final boolean passwordIsValid;

    public TestLoginUserDataAccess(boolean userExists, boolean passwordIsValid) {
        this.userExists = userExists;
        this.passwordIsValid = passwordIsValid;
    }

    @Override
    public boolean existsByName(String identifier) {
        return userExists;
    }

    @Override
    public User getUserByName(String username) {
        return new CommonUserFactory().create(username, "testPassword", LocalDateTime.now(), 0);
    }

    @Override
    public boolean validatePassword(String identifier, String password) {
        return passwordIsValid;
    }
}

/**
 * Fake implementation of LoginOutputBoundary for testing purposes.
 */


class TestLoginOutputBoundary implements LoginOutputBoundary {

    private boolean successViewCalled = false;
    private boolean failViewCalled = false;
    private String errorMessage;

    @Override
    public void prepareSuccessView(LoginOutputData user) {
        assertEquals("validUser", user.getUser().getName());
        successViewCalled = true;
    }

    @Override
    public void prepareFailView(UserDataAccessFailed error) {
        failViewCalled = true;
        errorMessage = error.getMessage();
    }

    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public boolean isFailViewCalled() {
        return failViewCalled;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}