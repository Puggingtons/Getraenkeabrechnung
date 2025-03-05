package validators;

import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.PasswordValidator;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    
    @Test
    void isValidPasswordShouldReturnTrue() {
        assertTrue(PasswordValidator.isValidPassword(new Password("goodPassword=1")));
    }

    @Test
    void isValidPasswordWithNoLowerShouldReturnFalse() {
        assertFalse(PasswordValidator.isValidPassword(new Password("noNumber#")));
    }

    @Test
    void isValidPasswordTooShortShouldReturnFalse() {
        assertFalse(PasswordValidator.isValidPassword(new Password("Short3@")));
    }

    @Test
    void testi() {
        assertFalse(PasswordValidator.isValidPassword(new Password("no!SpeChar1")));
    }

    @Test
    void testi2() {
        assertFalse(PasswordValidator.isValidPassword(new Password("NOLOWER#1")));
    }

    @Test
    void testi3() {
        assertFalse(PasswordValidator.isValidPassword(new Password("noupper#2")));
    }
    
}
