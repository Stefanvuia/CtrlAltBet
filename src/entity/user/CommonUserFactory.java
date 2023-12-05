package entity.user;

import java.time.LocalDateTime;

/**
 * Factory class responsible for creating instances of {@link CommonUser}.
 */
public class CommonUserFactory implements UserFactory {

    /**
     * Creates a new instance of {@link CommonUser}.
     *
     * @return A new {@link CommonUser} instance.
     */
    @Override
    public User create(String name, String password, LocalDateTime ltd, int balance) {
        return new CommonUser(name, password, ltd, balance);
    }
}
