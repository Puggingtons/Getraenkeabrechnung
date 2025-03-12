package user;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.Username;

class UserManagementTest {

    @Test
    void testValidUserCreation() {
        User user = new User(new Username("valid_User123"), new Password("goodPassword=1"));
        assertNotNull(user);
    }

    // Test will get implemented when validators are also implemented for User creation
    /* @Test
    void testInvalidUserCreation() {
        User user = new User(new Username("_invalidUser"), new Password("badPassword"));
        assertNull(user);
    } */

    @Test
    void testSetInvalidUsername() {
        User user = new User(new Username(null), new Password("goodPassword=1"));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(new Username("")));
    }

    @Test
    void testSetInvalidPassword() {
        User user = new User(new Username("valid_User123"), new Password(null));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(new Password("")));
    }

    // Tests will get implemented when a toString method is implemented for User
    /* @Test
    void testGetUsername() {
        User user = new User(new Username("valid_User123"), new Password("goodPassword=1"));
        assertEquals("valid_User123", user.getUsername());
    }

    @Test
    void testGetPassword() {
        User user = new User(new Username("valid_User123"), new Password("goodPassword=1"));
        assertEquals("goodPassword=1", user.getPassword());
    } */
}
