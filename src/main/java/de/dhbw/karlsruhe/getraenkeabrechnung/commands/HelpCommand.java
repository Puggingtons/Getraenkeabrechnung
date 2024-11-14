package de.dhbw.karlsruhe.getraenkeabrechnung.commands;

public class HelpCommand implements Command {
    private CommandRegistry commandRegistry;

    public HelpCommand(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "prints this message";
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public String execute() {
        return commandRegistry.usage();
    }
}
