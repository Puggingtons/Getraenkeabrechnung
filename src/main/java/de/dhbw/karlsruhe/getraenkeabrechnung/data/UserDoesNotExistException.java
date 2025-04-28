package de.dhbw.karlsruhe.getraenkeabrechnung.data;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
