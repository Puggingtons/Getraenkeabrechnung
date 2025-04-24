package de.dhbw.karlsruhe.getraenkeabrechnung.banking;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;

public class Account {
    private Money balance;

    public Account() {
        this.balance = new Money("0.00");
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
}
