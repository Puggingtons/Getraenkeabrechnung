package de.dhbw.karlsruhe.getraenkeabrechnung.data.banking;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
