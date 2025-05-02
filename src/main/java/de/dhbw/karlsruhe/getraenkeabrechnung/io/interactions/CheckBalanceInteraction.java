package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;

public class CheckBalanceInteraction extends Interaction<Money> {

    private final Account account;

    public CheckBalanceInteraction(Account account) {
        super();

        this.account = account;
    }

    @Override
    String usage() {
        return "Shows your current balance.";
    }

    @Override
    protected void execute() {
        success(account.getBalance());
    }
}
