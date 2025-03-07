package validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.dhbw.karlsruhe.getraenkeabrechnung.Email;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.EmailValidator;

class EmailValidatorTest {

    @Test
    void isValidEmailShouldReturnTrue() {
        assertTrue(EmailValidator.isValidEmail(new Email("mbw42233@jioso.com")));
    }
    
    @Test
    void isValidEmailWithSpecialCharactersShouldReturnTrue() {
        assertTrue(EmailValidator.isValidEmail(new Email("x.wm73-806@jioso.com")));
    }

    @Test
    void isValidEmailWithFourCharTLDShouldReturnTrue() {
        assertTrue(EmailValidator.isValidEmail(new Email("3@j.ocom")));
    }

    @Test
    void isValidEmailWithNoAtShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("mbw42233jioso.com")));
    }

    @Test
    void isValidEmailWithNoDotShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("mbw42233@jiosocom")));
    }

    @Test
    void isValidEmailWithNoAtAndNoDotShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("mbw42233jiosocom")));
    }

    @Test
    void isValidEmailWithNoTLDShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("mbw42233@jioso")));
    }

    @Test
    void isValidEmailWithNoUsernameShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("@jioso.com")));
    }

    @Test
    void isValidEmailWithNoDomainShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("mbw42233@.com")));
    }

    @Test
    void isValidEmailWithSpaceCharShouldReturnFalse() {
        assertFalse(EmailValidator.isValidEmail(new Email("mail@jio so.com")));
    }

}
