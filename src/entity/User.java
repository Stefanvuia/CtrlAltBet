package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface User {
    boolean passwordIsValid();

    String getName();

    LocalDateTime getCreationTime();
    String getPassword();
    int getBalance();
    void setBalance(int balance);
}
