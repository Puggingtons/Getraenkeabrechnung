package banking;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.NotEnoughMoneyException;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void itDeposits() {
        Account account = new Account(new Username("dings"));
        Money amount = new Money("12.34");

        account.deposit(amount);

        assertEquals(amount, account.getBalance());
    }

    @Test
    public void itCharges() {
        Account account = new Account(new Username("dings"));
        Money amount = new Money("12.34");

        account.deposit(amount);

        Money charged = account.charge(amount);

        assertEquals(amount, charged);
        assertEquals(new Money("0.00"), account.getBalance());
    }

    @Test
    public void itThrowsExceptionIfNotEnoughMoney() {
        Account account = new Account(new Username("dings"));
        Money amount = new Money("12.34");
        account.deposit(new Money("10.00"));

        assertThrows(NotEnoughMoneyException.class, () -> account.charge(amount));
    }

    @Test
    public void itEquals() {
        Account account1 = new Account(new Username("dings"));
        Account account2 = new Account(new Username("dings"));

        assertEquals(account1, account2);
    }

    @Test
    public void itDoesNotEqualBalance() {
        Account account1 = new Account(new Username("dings"));
        Account account2 = new Account(new Username("dings"));

        account2.deposit(new Money("10.00"));

        assertNotEquals(account1, account2);
    }

    @Test
    public void itDoesNotEqualName() {
        Account account1 = new Account(new Username("dings"));
        Account account2 = new Account(new Username("bums"));

        assertNotEquals(account1, account2);
    }
}
