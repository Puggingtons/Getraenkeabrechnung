package de.dhbw.karlsruhe.getraenkeabrechnung.logging;

public interface Logger
{
    void log(String message);

    Logger getInnerLogger();
}
