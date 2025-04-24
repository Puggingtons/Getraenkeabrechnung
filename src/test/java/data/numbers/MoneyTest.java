package data.numbers;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    public void itCanBeCreatedFromString() {
        String input = "12.34";
        BigDecimal amount = new BigDecimal(input);

        assertEquals(amount, new Money(input).getAmount());
    }

    @Test
    public void itEquals() {
        Money money1 = new Money("12.34");
        Money money2 = new Money("12.34");

        assertEquals(money1, money2);
    }

    @Test
    public void itDoesNotEqual() {
        Money money1 = new Money("12.34");
        Money money2 = new Money("12.35");

        assertNotEquals(money1, money2);
    }

    @Test
    public void itEqualsMoreZeroes() {
        Money money1 = new Money("12.0");
        Money money2 = new Money("12.00");

        assertEquals(money1, money2);
    }

    @Test
    public void itDoesNotMutateWhenAdding() {
        BigDecimal amount = new BigDecimal("12.34");
        BigDecimal sum = new BigDecimal("24.68");
        Money money = new Money(amount);
        Money money2 = money.add(money);

        assertEquals(amount, money.getAmount());
        assertEquals(sum, money2.getAmount());
    }

    @Test
    public void itDoesNotMutateWhenSubtracting() {
        BigDecimal amount = new BigDecimal("12.34");
        BigDecimal difference = new BigDecimal("0.00");
        Money money = new Money(amount);
        Money money2 = money.subtract(money);

        assertEquals(amount, money.getAmount());
        assertEquals(difference, money2.getAmount());
    }

    @Test
    public void itDoesNotMutateWhenMultiplying() {
        BigDecimal amount = new BigDecimal("12.34");
        BigDecimal product = new BigDecimal("24.68");
        Money money = new Money(amount);
        Money money2 = money.multiply(2);

        assertEquals(amount, money.getAmount());
        assertEquals(product, money2.getAmount());
    }

    @Test
    public void itRoundsUp() {
        BigDecimal amount = new BigDecimal("0.499");
        BigDecimal rounded = new BigDecimal("0.50");

        Money money = new Money(amount);

        assertEquals(rounded, money.getAmount());
    }
}
