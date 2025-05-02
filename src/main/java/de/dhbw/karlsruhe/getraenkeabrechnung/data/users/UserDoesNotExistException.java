package de.dhbw.karlsruhe.getraenkeabrechnung.data.users;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
