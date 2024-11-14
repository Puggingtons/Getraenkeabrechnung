package de.dhbw.karlsruhe.getraenkeabrechnung.commands;

import de.dhbw.karlsruhe.getraenkeabrechnung.Getraenkeabrechnung;

public class LogoutCommand implements Command {
    Getraenkeabrechnung getraenkeabrechnung;

    public LogoutCommand(Getraenkeabrechnung getraenkeabrechnung) {
        this.getraenkeabrechnung = getraenkeabrechnung;
    }

    @Override
    public String getName() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "logout the user";
    }

    @Override
    public String getUsage() {
        return "logout";
    }

    @Override
    public String execute() {
        String username = getraenkeabrechnung.getUser().getUsername();
        getraenkeabrechnung.logout();
        return "Logged out " + username + " successfully!";
    }
}
