package users;

import entity.User;
import entity.UserFactory;


import java.time.LocalDateTime;

public class UserSignupInteractor implements UserSignupInputBoundary {
    final UserSignupDataAccessInterface userDsGateway;
    final UserSignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public UserSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface, UserSignupOutputBoundary userSignupOutputBoundary,
                                UserFactory userFactory) {
        this.userDsGateway = userSignupDataAccessInterface;
        this.userPresenter = userSignupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void createUser(UserSignupInputData userSignupInputData) {
        if (userDsGateway.existsByName(userSignupInputData.getName())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!userSignupInputData.getPassword().equals(userSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }

        LocalDateTime now = LocalDateTime.now();
        User user = userFactory.create(userSignupInputData.getName(), userSignupInputData.getPassword(), now);
        if (!user.passwordIsValid()) {
            userPresenter.prepareFailView("User password must have more than 5 characters.");
        }

        userDsGateway.save(user);

        UserSignupOutputData accountResponseModel = new UserSignupOutputData(user.getName(), now.toString());
        userPresenter.prepareSuccessView(accountResponseModel);
    }
}