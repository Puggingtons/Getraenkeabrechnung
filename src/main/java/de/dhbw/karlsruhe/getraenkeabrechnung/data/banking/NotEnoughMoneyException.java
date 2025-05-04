package de.dhbw.karlsruhe.getraenkeabrechnung.data.banking;

public class NotEnoughMoneyException extends RuntimeException
{
    public NotEnoughMoneyException(String message)
    {
        super(message);
    }
}
