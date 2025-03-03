package de.dhbw.karlsruhe.getraenkeabrechnung;

public class Konto {

    private double accountCredit;

    public double getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(double accountCredit) {
        this.accountCredit = accountCredit;
    }

    public double addAccountCredit(double accountCredit) {
        this.accountCredit = getAccountCredit()+accountCredit;
        setAccountCredit(this.accountCredit);
        return this.accountCredit;
    }

    public double removeAccountCredit(double accountCredit) {
        this.accountCredit = getAccountCredit()-accountCredit;
        setAccountCredit(this.accountCredit);
        return this.accountCredit;
    }

}
