package validators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.validators.UsernameValidator;

class UsernameValidatorTest {


    // Tests for Rule 1: Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
    @Test
    void isValidUsernameBasicShouldReturnTrue() {
        assertTrue(UsernameValidator.isValid(new Username("valid_User123")));
    }


    // Test for Rule 2: Username allowed of the dot (.), underscore (_), and hyphen (-).
    @Test
    void isValidUsernameSpecialCharsShouldReturnTrue() {
        assertTrue(UsernameValidator.isValid(new Username("a-valid_u.ser123")));
    }


    // Tests for Rule 3: The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
    @Test
    void isValidUsernameWithUnderscoreStartShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("_invalidUser123")));
    }

    @Test
    void isValidUsernameWithDotStartShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username(".invalidUser123")));
    }

    @Test
    void isValidUsernameWithHyphenStarthouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("-invalidUser123")));
    }

    @Test
    void isValidUsernameWithUnderscoreEndShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalidUser123_")));
    }

    @Test
    void isValidUsernameWithDotEndShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalidUser123.")));
    }

    @Test
    void isValidUsernameWithHyphenEndhouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalidUser123-")));
    }


    // Tests for rule 4: The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
    @Test
    void isValidUsernameConsecutiveDotSpecialCharShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalid..user1234")));
    }

    @Test
    void isValidUsernameConsecutiveUnderscoreSpecialCharShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalid__user1234")));
    }

    @Test
    void isValidUsernameConsecutiveHyphenSpecialCharShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalid--user1234")));
    }

    @Test
    void isValidUsernameConsecutiveMixedSpecialCharShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("invalid-_-._user1234")));
    }


    // Tests for rule 5: The number of characters must be between 5 to 20.
    @Test
    void isValidUsernameTooShortShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("in_3")));
    }

    @Test
    void isValidUsernameTooLongShouldReturnFalse() {
        assertFalse(UsernameValidator.isValid(new Username("in-validUser123456789")));
    }   
}
