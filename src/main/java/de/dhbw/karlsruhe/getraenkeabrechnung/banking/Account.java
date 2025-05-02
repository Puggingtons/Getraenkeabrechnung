package de.dhbw.karlsruhe.getraenkeabrechnung.banking;

import de.dhbw.karlsruhe.getraenkeabrechnung.validatables.Username;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;

public class Account {
    private final Username username;
    private Money balance;

    public Account(Username username) {
        this.username = username;
        this.balance = new Money("0.00");
    }

    public Username getUsername() {
        return username;
    }

    public Money getBalance() {
        return balance;
    }

    public void deposit(Money amount) {
        balance = balance.add(amount);
    }

    public Money charge(Money amount) throws NotEnoughMoneyException {
        if (amount.getAmount().compareTo(balance.getAmount()) > 0) {
            throw new NotEnoughMoneyException("Not enough money in account! (available: " + balance + ", charged: " + amount + ")");
        }

        balance = balance.subtract(amount);
        return amount;
    }

    public boolean isEmpty() {
        return balance.getAmount().compareTo(
            new Money("0.00").getAmount()
            ) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account other = (Account) o;

        return username.equals(other.username) && balance.equals(other.balance);
    }
}
