package validators;

import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Password;
import de.dhbw.karlsruhe.getraenkeabrechnung.PasswordManagementException;
import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.validators.PasswordValidator;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    
    @Test
    void isValidPasswordShouldReturnTrue() {
        assertTrue(PasswordValidator.isValid(new Password("goodPassword=1")));
    }

    @Test
    void isValidPasswordWithNoLowerShouldReturnFalse() {
        assertFalse(PasswordValidator.isValid(new Password("noNumber#")));
    }

    @Test
    void isValidPasswordTooShortShouldReturnFalse() {
        assertFalse(PasswordValidator.isValid(new Password("Short3@")));
    }

    @Test
    void isValidPasswordNoSpecialCharShouldReturnFalse() {
        assertFalse(PasswordValidator.isValid(new Password("no!SpeChar1")));
    }

    @Test
    void isValidPasswordNoLowerCharShouldReturnFalse() {
        assertFalse(PasswordValidator.isValid(new Password("NOLOWER#1")));
    }

    @Test
    void isValidPasswordNoUpperCharShouldReturnFalse() {
        assertFalse(PasswordValidator.isValid(new Password("noupper#2")));
    }

    @Test
    void isPasswordHashedCorrectlyShouldReturnTrue() throws PasswordManagementException {
        Password password = new Password("goodPassword=1");
        password.hashPassword();
        assertTrue(password.verifyPassword("goodPassword=1", password.getHashedPassword(), password.getSalt()));
    }

}
