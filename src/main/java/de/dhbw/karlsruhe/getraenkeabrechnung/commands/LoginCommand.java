package de.dhbw.karlsruhe.getraenkeabrechnung.commands;

import de.dhbw.karlsruhe.getraenkeabrechnung.Getraenkeabrechnung;
import de.dhbw.karlsruhe.getraenkeabrechnung.User;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.cli.Cli;

public class LoginCommand implements Command {
    private Cli cli;
    private Getraenkeabrechnung getraenkeabrechnung;

    public LoginCommand(Cli cli, Getraenkeabrechnung getraenkeabrechnung) {
        this.cli = cli;
        this.getraenkeabrechnung = getraenkeabrechnung;
    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "logs in the user";
    }

    @Override
    public String getUsage() {
        return "login";
    }

    @Override
    public String execute() {
        String username = cli.promptInput("please enter your username:");
        String password = cli.promptInput("please enter your password:");

        User user = new User(username, password);
        getraenkeabrechnung.login(user);

        return "Welcome " + username + "!\n";
    }
}
