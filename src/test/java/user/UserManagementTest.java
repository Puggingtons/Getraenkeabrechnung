package user;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;

class UserManagementTest {

    @Test
    void testValidUserCreation() {
        User user = new User(new Username(null), new Password(null));
        user.setUsername(new Username("valid_User123"));
        user.setPassword(new Password("goodPassword=1"));
        assertNotNull(user);
    }

    @Test
    void testInvalidUserCreationWithEmptyUsername() {
        User user = new User(new Username(null), new Password("goodPassword=1"));
        assertThrows(NullPointerException.class, () -> user.setUsername(new Username(null)));
    }

    @Test
    void testInvalidUserCreationWithEmptyPassword() {
        User user = new User(new Username("valid_User123"), new Password(null));
        assertThrows(NullPointerException.class, () -> user.setPassword(new Password(null)));
    }
}
