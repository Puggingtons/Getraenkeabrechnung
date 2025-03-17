package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.Getraenkeabrechnung;

import java.util.HashMap;
import java.util.Map;

public class MainInteraction implements Interaction<Void> {

    private final Map<String, Interaction> interactions;
    private final SelectInteraction selectInteraction;

    private final Getraenkeabrechnung getraenkeabrechnung;

    public MainInteraction(Getraenkeabrechnung getraenkeabrechnung) {
        this.interactions = new HashMap<>();
        this.selectInteraction = new SelectInteraction();

        this.getraenkeabrechnung = getraenkeabrechnung;

        addExitInteraction();
        addRegisterInteraction();
        addExampleSelectInteraction();
        addExampleStringInteraction();
        addLoginInteraction();
        addLogoutInteraction();
    }

    public void addInteraction(String key, String description, Interaction interaction) {
        this.interactions.put(key, interaction);
        this.selectInteraction.addOption(key, description);
    }

    @Override
    public void explain() {
        this.selectInteraction.explain();
    }

    @Override
    public Void run() {
        explain();
        while (true) {
            String selection = this.selectInteraction.run();

            if (selection.equals("exit")) {
                // https://stackoverflow.com/questions/5568409/java-generics-void-void-types
                return null;
            }

            Interaction interaction = this.interactions.get(selection);

            if (interaction == null) {
                System.out.println("unknown command: " + selection);
                continue;
            }

            interaction.explain();
            interaction.run();
        }
    }

    private void addExitInteraction() {
        addInteraction("exit", "exits the program", null);
    }

    private void addRegisterInteraction() {
        CreateUserInteraction interaction = new CreateUserInteraction();
        addInteraction("register", "Register a new user", interaction);
    }

    private void addLoginInteraction() {
        LoginInteraction interaction = new LoginInteraction();
        interaction.onSuccess(getraenkeabrechnung::login);
        addInteraction("login", "Login a user", interaction);
    }

    private void addLogoutInteraction() {
        LogoutInteraction interaction = new LogoutInteraction();
        interaction.onSuccess((_) -> getraenkeabrechnung.logout());
        addInteraction("logout", "Logout a user", interaction);
    }

    private void addExampleSelectInteraction() {
        SelectInteraction interaction = new SelectInteraction();

        interaction.addOption("key", "Option 0");
        interaction.pushOption("Option 1");
        interaction.pushOption("Option 2");
        interaction.pushOption("Option 3");

        addInteraction("select", "Select an option", interaction);
    }

    private void addExampleStringInteraction() {
        StringInputInteraction interaction = new StringInputInteraction("Please enter a valid String!", "> ");

        addInteraction("string", "Enter a valid string", interaction);
    }
}
