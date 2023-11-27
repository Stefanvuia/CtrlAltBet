package entity.user;

import java.time.LocalDateTime;

public interface User {
    boolean passwordIsValid();

    String getName();

    LocalDateTime getCreationTime();

    String getPassword();

    int getBalance();

    void editBalance(int balance);
}
