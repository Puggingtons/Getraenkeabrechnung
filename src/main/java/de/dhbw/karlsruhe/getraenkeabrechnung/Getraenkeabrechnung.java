package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.commands.CommandRegistry;
import de.dhbw.karlsruhe.getraenkeabrechnung.commands.HelpCommand;
import de.dhbw.karlsruhe.getraenkeabrechnung.commands.LoginCommand;
import de.dhbw.karlsruhe.getraenkeabrechnung.commands.LogoutCommand;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.cli.Cli;

public class Getraenkeabrechnung {

    private final Cli cli;
    private CommandRegistry commandRegistry;

    private User user;

    public Getraenkeabrechnung(Cli cli) {
        this.cli = cli;

        this.commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand(new HelpCommand(commandRegistry));
        commandRegistry.registerCommand(new LoginCommand(cli, this));
        commandRegistry.registerCommand(new LogoutCommand(this));

        greet();
    }

    private void greet() {
        cli.printMessage("""
                #######################
                # Getraenkeabrechnung #
                #######################
                
                """);
    }

    public void start() {
        while (true) {
            String input = cli.promptInput("What do you want to do?");

            cli.printMessage(commandRegistry.execute(input));
        }
    }

    private void printHelp() {
        cli.printMessage("""
                Possible commands:
                - help
                - login
                - logout
                - exit
                """);
    }

    public void login(User user) {
        this.user = user;
        updatePromptPrefix();
    }

    public void logout() {
        user = null;
        updatePromptPrefix();
    }

    public User getUser() {
        return user;
    }

    private void updatePromptPrefix() {
        if (user == null) {
            cli.setPromptPrefix(Cli.DEFAULT_PROMPT_PREFIX);
        } else {
            cli.setPromptPrefix("[" + user.getUsername() + "]" + Cli.DEFAULT_PROMPT_PREFIX);
        }
    }
}
