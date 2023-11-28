package use_case.launch_menu.signup;

import constants.Constants;
import entity.user.User;
import entity.user.UserFactory;
import entity.user.UserDataAccessFailed;
import use_case.account_menu.history.HistoryDataAccessInterface;


import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDsGateway;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    final HistoryDataAccessInterface historyDAO;

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface, SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory, HistoryDataAccessInterface historyDAO) {
        this.userDsGateway = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.historyDAO = historyDAO;
    }

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

            SignupOutputData accountResponseModel = new SignupOutputData(user.getName(), now.toString());
            userPresenter.prepareSuccessView(accountResponseModel);
        }
    }
}