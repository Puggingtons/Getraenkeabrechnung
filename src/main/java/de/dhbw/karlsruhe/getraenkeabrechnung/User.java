package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.banking.Account;
import de.dhbw.karlsruhe.getraenkeabrechnung.rights.Right;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.PasswordValidator;
import de.dhbw.karlsruhe.getraenkeabrechnung.validators.UsernameValidator;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class User {
    private Username username;
    private Password password;
    private String realFirstName;
    private String realLastName;
    private String realName;
    private Email email;

    private Set<Right> rights;

    public User(Username username, Password password, String realFirstName, String realLastName,
                String realName, Email email) {

        this.username = username;
        this.password = password;
        this.realFirstName = realFirstName;
        this.realLastName = realLastName;
        this.email = email;
        this.realName = realFirstName + " " + realLastName;

        this.rights = new HashSet<>();
    }

    // Default constructor
    public User() {
    }

    // USE THIS CONSTRUCTOR ONLY FOR TESTING PURPOSES
    public User(Username username, Password password) {
        this.username = username;
        this.password = password;
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

    public void nullPassword() {
        password.nullPasswordString();
    }

    private void hashAndSetPassword(Password password) {
        try {
            password.hashPassword();
        } catch (IllegalArgumentException | PasswordManagementException e) {
            throw new IllegalArgumentException("Password not valid!");
        }
        password.getHashedPassword();
    }

    public boolean verifyPassword(String providedPassword) {
        try {
            return Password.verifyPassword(providedPassword, password.getHashedPassword(), password.getSalt());
        } catch (PasswordManagementException e) {
            throw new IllegalArgumentException("Password verification failed!", e);
        }
    }
    public String getHashedPassword() {
        return password.getHashedPassword();
    }

    public String getSalt() {
        return password.getSalt();
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

    public void addRights(Collection<Right> rights) {
        this.rights.addAll(rights);
    }

    @Override
    public String toString() {
        return "Username: " + this.getUsername().toString();
    }

}
