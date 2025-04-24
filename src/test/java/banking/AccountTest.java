package banking;

import de.dhbw.karlsruhe.getraenkeabrechnung.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.banking.NotEnoughMoneyException;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    public void itDeposits() {
        Account account = new Account();
        Money amount = new Money("12.34");

        account.deposit(amount);

        assertEquals(amount, account.getBalance());
    }

    @Test
    public void itCharges() {
        Account account = new Account();
        Money amount = new Money("12.34");

        account.deposit(amount);

        Money charged = account.charge(amount);

        assertEquals(amount, charged);
        assertEquals(new Money("0.00"), account.getBalance());
    }

    @Test
    public void itThrowsExceptionIfNotEnoughMoney() {
        Account account = new Account();
        Money amount = new Money("12.34");
        account.deposit(new Money("10.00"));

        assertThrows(NotEnoughMoneyException.class, () -> account.charge(amount));
    }
}
