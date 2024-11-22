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

                  _____    _   _               ____     ____     _____   __   __   ____     _       _        ____  
                 |_ \" _|  |'| |'|     ___   U |  _\"\\ u / __\"| u |_ \" _|  \\ \\ / /U /\"___|U  /\"\\  u  |\"|    U /\"___| 
                   | |   /| |_| |\\   | \"_|   \\| |_) |/<\\___ \\/    | |     \\ V / \\| | u   \\/ _ \\/ U | | u  \\| | u   
                  /| |\\  U|  _  |u    | |     |  _ <   u___) |   /| |\\   U_|\"|_u | |/__  / ___ \\  \\| |/__  | |/__  
                 u |_|U   |_| |_|   U/| |\\u   |_| \\_\\  |____/>> u |_|U     |_|    \\____|/_/   \\_\\  |_____|  \\____| 
                 _// \\\\_  //   \\\\.-,_|___|_,-.//   \\\\_  )(  (__)_// \\\\_.-,//|(_  _// \\\\  \\\\    >>  //  \\\\  _// \\\\  
                (__) (__)(_\" )(\"_)\\_)-' '-(_/(__)  (__)(__)    (__) (__)\\_) (__)(__)(__)(__)  (__)(_\" )(\"_)(__)(__) 

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
