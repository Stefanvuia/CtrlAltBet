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

    @Test
    void editBalance() {
        CommonUser user = new CommonUser("Alice", "securepass", LocalDateTime.now(), 200);
        assertEquals(200, user.getBalance());

        user.editBalance(50);
        assertEquals(250, user.getBalance());

        user.editBalance(-30);
        assertEquals(220, user.getBalance());
    }

}
class CommonUserFactoryTest {

    @Test
    void create() {
        CommonUserFactory factory = new CommonUserFactory();
        User user = factory.create("Bob", "strongpass", LocalDateTime.now(), 300);

        assertEquals("Bob", user.getName());
        assertEquals("strongpass", user.getPassword());
        assertEquals(300, user.getBalance());
    }
}