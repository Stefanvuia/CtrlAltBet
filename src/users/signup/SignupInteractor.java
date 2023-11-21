package users.signup;

import Constants.Constants;
import entity.User;
import entity.UserFactory;


import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDsGateway;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface, SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDsGateway = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void createUser(SignupInputData signupInputData) {
        if (userDsGateway.existsByName(signupInputData.getName())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }

        LocalDateTime now = LocalDateTime.now();
        User user = userFactory.create(signupInputData.getName(), signupInputData.getPassword(), now, Constants.INITIAL_BALANCE);
        if (!user.passwordIsValid()) {
            userPresenter.prepareFailView("User password must have more than 5 characters.");
        }

        userDsGateway.save(user);

        SignupOutputData accountResponseModel = new SignupOutputData(user.getName(), now.toString());
        userPresenter.prepareSuccessView(accountResponseModel);
    }
}