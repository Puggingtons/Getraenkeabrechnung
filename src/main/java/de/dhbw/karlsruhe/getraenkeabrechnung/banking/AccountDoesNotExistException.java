package de.dhbw.karlsruhe.getraenkeabrechnung.banking;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
