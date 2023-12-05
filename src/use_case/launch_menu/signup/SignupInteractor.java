package use_case.launch_menu.signup;

import constants.Constants;
import entity.user.User;
import entity.user.UserFactory;
import entity.user.UserDataAccessFailed;
import use_case.account_menu.history.HistoryDataAccessInterface;

import java.time.LocalDateTime;

/**
 * The {@code SignupInteractor} class implements the {@code SignupInputBoundary} interface and is responsible
 * for handling user signup interactions in the launch menu.
 */
public class SignupInteractor implements SignupInputBoundary {

    /**
     * The interface for accessing user data during signup.
     */
    private final SignupUserDataAccessInterface userDsGateway;

    /**
     * The presenter responsible for handling the output of user signup interactions.
     */
    private final SignupOutputBoundary userPresenter;

    /**
     * The factory responsible for creating user objects.
     */
    private final UserFactory userFactory;

    /**
     * The interface for accessing user history data during signup.
     */
    private final HistoryDataAccessInterface historyDAO;

    /**
     * Constructs a {@code SignupInteractor} with the specified {@code signupUserDataAccessInterface},
     * {@code signupOutputBoundary}, {@code userFactory}, and {@code historyDAO}.
     *
     * @param signupUserDataAccessInterface The interface for accessing user data during signup.
     * @param signupOutputBoundary The presenter responsible for handling the output of user signup interactions.
     * @param userFactory The factory responsible for creating user objects.
     * @param historyDAO The interface for accessing user history data during signup.
     */
    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface, SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory, HistoryDataAccessInterface historyDAO) {
        this.userDsGateway = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.historyDAO = historyDAO;
    }

    /**
     * Initiates the process of creating a new user based on the provided {@code signupInputData}.
     *
     * <p>If the user already exists or the passwords do not match, a fail view is prepared with an appropriate message.
     * Otherwise, a new user is created, and if the password is valid, it is saved along with user history.</p>
     *
     * @param signupInputData The input data containing information necessary for user signup.
     */
    @Override
    public void createUser(SignupInputData signupInputData) {
        if (userDsGateway.existsByName(signupInputData.getName())) {
            userPresenter.prepareFailView(new UserDataAccessFailed("User already exists"));
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView(new UserDataAccessFailed("Passwords do not match"));
        } else {
            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getName(), signupInputData.getPassword(), now, Constants.INITIAL_BALANCE);
            if (!user.passwordIsValid()) {
                userPresenter.prepareFailView(new UserDataAccessFailed("User password must have more than 5 characters"));
                return;
            }

            userDsGateway.save(user);
            historyDAO.addUser(user.getName());

            SignupOutputData accountResponseModel = new SignupOutputData(user.getName());
            userPresenter.prepareSuccessView(accountResponseModel);
        }
    }
}