package entity.user;

import org.junit.Before;
import org.junit.Test;
import use_case.account_menu.sign_out.SignOutInteractor;
import use_case.account_menu.sign_out.SignOutOutputBoundary;

import static org.junit.Assert.assertTrue;

// Stub implementation for SignOutOutputBoundary
class StubSignOutPresenter implements SignOutOutputBoundary {
    private boolean prepareWelcomeViewCalled = false;

    @Override
    public void prepareWelcomeView() {
        prepareWelcomeViewCalled = true;
    }

    public boolean isPrepareWelcomeViewCalled() {
        return prepareWelcomeViewCalled;
    }
}

public class SignOutInteractorTest {

    private StubSignOutPresenter stubSignOutPresenter;
    private SignOutInteractor signOutInteractor;

    @Before
    public void setUp() {
        stubSignOutPresenter = new StubSignOutPresenter();
        signOutInteractor = new SignOutInteractor(stubSignOutPresenter);
    }

    @Test
    public void testExecute() {
        // Trigger the execute method
        signOutInteractor.execute();

        // Verify that the prepareWelcomeView method of the stub presenter is called
        assertTrue(stubSignOutPresenter.isPrepareWelcomeViewCalled());
    }
}