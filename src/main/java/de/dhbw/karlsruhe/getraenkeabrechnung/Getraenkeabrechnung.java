package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.cli.Cli;

public class Getraenkeabrechnung {

    private final Cli cli;

    public Getraenkeabrechnung(Cli cli) {
        this.cli = cli;

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

            switch (input) {
                case "login":
                    login();
                    break;
                case "logout":
                    logout();
                    break;
                case "exit":
                    return;
                case "help":
                default:
                    printHelp();
                    break;
            }
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

    private void login() {
        String username = cli.promptInput("please enter your username:");
        String password = cli.promptInput("please enter your password:");
        cli.printMessage("Hello " + username + " !");
        cli.setPromptPrefix("[" + username + "]" + Cli.DEFAULT_PROMPT_PREFIX);
    }

    private void logout() {
        cli.setPromptPrefix(Cli.DEFAULT_PROMPT_PREFIX);
    }
}
