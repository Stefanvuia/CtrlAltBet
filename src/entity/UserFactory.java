package entity;

import users.UserSignupDsData;

import java.time.LocalDateTime;

public interface UserFactory {
    User create(String name, String password, LocalDateTime ltd);
}
