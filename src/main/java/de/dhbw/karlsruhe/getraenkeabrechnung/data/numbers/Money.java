package de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money
{
    private final BigDecimal amount;

    public Money()
    {
        this.amount = BigDecimal.ZERO;
    }

    public Money(String amount)
    {
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public Money(BigDecimal amount)
    {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    /**
     * Adds two Money objects together and returns a new Money object.
     *
     * @param money the Money object to add to this one
     * @return a new Money object with the sum of the two amounts
     */
    public Money add(Money money)
    {
        return new Money(this.amount.add(money.getAmount()));
    }

    /**
     * Subtracts the given Money object from this one and returns a new Money object.
     *
     * @param money the Money object to subtract from this one
     * @return a new Money object with the difference of the two amounts
     */
    public Money subtract(Money money)
    {
        return new Money(this.amount.subtract(money.getAmount()));
    }

    /**
     * Multiplies the amount by the given multiplier and returns a new Money object.
     *
     * @param multiplier the multiplier to multiply the amount with
     * @return a new Money object with the product of the two amounts
     */
    public Money multiply(int multiplier)
    {
        return new Money(this.amount.multiply(new BigDecimal(multiplier)));
    }

    @Override
    public String toString()
    {
        return amount.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }
}
