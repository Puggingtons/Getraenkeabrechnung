package validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Email;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.validators.EmailValidator;

class EmailValidatorTest {

    @Test
    void isValidEmailShouldReturnTrue() {
        assertTrue(EmailValidator.isValid(new Email("mbw42233@jioso.com")));
    }
    
    @Test
    void isValidEmailWithSpecialCharactersShouldReturnTrue() {
        assertTrue(EmailValidator.isValid(new Email("x.wm73-806@jioso.com")));
    }

    @Test
    void isValidEmailWithFourCharTLDShouldReturnTrue() {
        assertTrue(EmailValidator.isValid(new Email("3@j.ocom")));
    }

    @Test
    void isValidEmailWithNoAtShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("mbw42233jioso.com")));
    }

    @Test
    void isValidEmailWithNoDotShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("mbw42233@jiosocom")));
    }

    @Test
    void isValidEmailWithNoAtAndNoDotShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("mbw42233jiosocom")));
    }

    @Test
    void isValidEmailWithNoTLDShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("mbw42233@jioso")));
    }

    @Test
    void isValidEmailWithNoUsernameShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("@jioso.com")));
    }

    @Test
    void isValidEmailWithNoDomainShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("mbw42233@.com")));
    }

    @Test
    void isValidEmailWithSpaceCharShouldReturnFalse() {
        assertFalse(EmailValidator.isValid(new Email("mail@jio so.com")));
    }

}
