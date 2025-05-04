package de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables;

public class PasswordManagementException extends Exception
{
    public PasswordManagementException(String message)
    {
        super(message);
    }

    public PasswordManagementException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PasswordManagementException(Throwable cause)
    {
        super(cause);
    }
}
