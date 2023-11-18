package entity;

import java.time.LocalDateTime;

public interface User {
    boolean passwordIsValid();

    String getName();

    LocalDateTime getCreationTime();
    String getPassword();
}
