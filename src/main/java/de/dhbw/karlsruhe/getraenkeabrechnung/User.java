package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.rights.Right;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.PasswordValidator;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.UsernameValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Todo 
// 1. login method (Session etc.)
// 2. Klassen für Feld Passwort erstellen

public class User {
    private Username username;
    private Password password;
    private String hashedPassword;
    private String salt;
    private String realFirstName;
    private String realLastName;
    private String realName;
    private Email email;
    private Konto konto;

    private Set<Right> rights;

    public User(Username username, Password password, String realFirstName, String realLastName,
                String realName, Email email, Konto konto) {

        this.username = username;
        this.password = password;
        this.realFirstName = realFirstName;
        this.realLastName = realLastName;
        this.email = email;
        this.realName = realFirstName + " " + realLastName;
        this.konto = new Konto();
        konto.setAccountCredit(0);

        this.rights = new HashSet<>();
    }

    // Constructor for class LoginCommand
    public User(Username username, Password password) {
        this.username = username;
        this.password = password;
        hashAndSetPassword(password);
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        if (UsernameValidator.isValidUsername(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username not valid!");
        }
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        if (PasswordValidator.isValidPassword(password)) {
            this.password = password;
            hashAndSetPassword(password);
        } else {
            throw new IllegalArgumentException("Password not valid!");
        }
    }

    private void hashAndSetPassword(Password password) {
        String hash = password.hashPassword();

        this.hashedPassword = hash;
        this.salt = password.getSalt();
    }

    public boolean verifyPassword(String providedPassword) {
        return Password.verifyPassword(providedPassword, this.hashedPassword, this.salt);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getRealFirstName() {
        return realFirstName;
    }

    public void setRealFirstName(String realFirstName) {
        this.realFirstName = realFirstName;
    }

    public String getRealLastName() {
        return realLastName;
    }

    public void setRealLastName(String realLastName) {
        this.realLastName = realLastName;
    }

    public String getRealName() {
        realName = realFirstName + " " + realLastName;
        return realName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public void addCreditAmount(double credit) {
        konto.addAccountCredit(credit);
    }

    public void removeCreditAmount(double credit) {
        konto.removeAccountCredit(credit);
    }

    public double getAccountCredit() {
        return konto.getAccountCredit();
    }

    public void addRights(Collection<Right> rights) {
        this.rights.addAll(rights);
    }

    @Override
    public String toString() {
        return "Username: " + this.getUsername().toString();
    }

}
