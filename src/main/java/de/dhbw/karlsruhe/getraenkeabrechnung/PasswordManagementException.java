package de.dhbw.karlsruhe.getraenkeabrechnung;

public class PasswordManagementException extends Exception {
    public PasswordManagementException(String message) {
        super(message);
    }
    public PasswordManagementException(String message, Throwable cause) {
        super(message, cause);
    }
    public PasswordManagementException(Throwable cause) {
        super(cause);
    }
}
