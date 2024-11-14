package de.dhbw.karlsruhe.getraenkeabrechnung.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, Command> commands;

    public CommandRegistry() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    public void removeCommand(Command command) {
        this.commands.remove(command.getName());
    }

    public Collection<Command> getCommands() {
        return this.commands.values();
    }

    public String execute(String key) {
        Command command = commands.get(key);

        if (command == null) {
            return usage();
        }

        return command.execute();
    }

    public String usage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        for (Command command : getCommands()) {
            sb.append("- ");
            sb.append(command.getUsage());
            sb.append("\n");
        }
        return sb.toString();
    }
}
