package entity.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    private CommonUser user;

    @BeforeEach
    void init() {
        user = new CommonUser("kevin", "abc", LocalDateTime.now(), 500);
    }

    @Test
    void passwordIsValid() {
        assertEquals(false, user.passwordIsValid());
    }
}