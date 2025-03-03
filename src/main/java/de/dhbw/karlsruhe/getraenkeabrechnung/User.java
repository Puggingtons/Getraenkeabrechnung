package de.dhbw.karlsruhe.getraenkeabrechnung;

    // Todo 
    // 1. login method (Session etc.)
    // 2. Klassen für Feld Passwort erstellen

public class User {
    private Username username;
    private Password password;
    private String realFirstName;
    private String realLastName;
    private String realName;
    private String email;
    private Konto konto;
    private GetraenkeAutomat getraenkeAutomat;
    private Rights rights;

    public User(Username username, Password password, String realFirstName, String realLastName,
        String realName, String email, Konto konto, GetraenkeAutomat getraenkeAutomat, Rights rights) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.realName = realFirstName + " " + realLastName;
        this.getraenkeAutomat = new GetraenkeAutomat();
        this.konto = new Konto();
        konto.setAccountCredit(0);
        this.rights = rights;
    }

    // Constructor for class LoginCommand
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
        } else {
            throw new IllegalArgumentException("Password not valid!");
        }
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

        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public GetraenkeAutomat getGetraenkeAutomat() {
        return getraenkeAutomat;
    }

    public void setGetraenkeAutomat(GetraenkeAutomat getraenkeAutomat) {
        this.getraenkeAutomat = getraenkeAutomat;
    }

    public void addCreditAmount(double credit) {
        konto.addAccountCredit(credit);
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public void removeCreditAmount(double credit) {
        konto.removeAccountCredit(credit);
    }

    public double getAccountCredit() {
        return konto.getAccountCredit();
    }

}
