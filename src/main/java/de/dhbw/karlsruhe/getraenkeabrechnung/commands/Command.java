package de.dhbw.karlsruhe.getraenkeabrechnung.commands;

public interface Command {
    String getName();

    String getDescription();

    String getUsage();

    String execute();
}
