package de.dhbw.karlsruhe.getraenkeabrechnung.banking;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
