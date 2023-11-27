package entity.user;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    @Override
    public User create(String name, String password, LocalDateTime ltd, int balance) {
        return new CommonUser(name, password, ltd, balance);
    }
}
