package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import java.util.HashMap;
import java.util.Map;

public class MainInteraction implements Interaction<Void> {

    private final Map<String, Interaction> interactions;
    private final SelectInteraction selectInteraction;

    public MainInteraction() {
        this.interactions = new HashMap<>();
        this.selectInteraction = new SelectInteraction();

        addExitInteraction();
        addRegisterInteraction();
        addExampleSelectInteraction();
        addExampleStringInteraction();
        // addLoginInteraction(); todo
        // addLogoutInteraction(); todo
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
        CreateUserInteraction createUserInteraction = new CreateUserInteraction();
        addInteraction("register", "Register a new user", createUserInteraction);
    }

    private void addExampleSelectInteraction() {
        SelectInteraction selectInteraction = new SelectInteraction();

        selectInteraction.addOption("key", "Option 0");
        selectInteraction.pushOption("Option 1");
        selectInteraction.pushOption("Option 2");
        selectInteraction.pushOption("Option 3");

        addInteraction("select", "Select an option", selectInteraction);
    }

    private void addExampleStringInteraction() {
        StringInputInteraction stringInputInteraction = new StringInputInteraction("Please enter a valid String!", "> ");

        addInteraction("string", "Enter a valid string", stringInputInteraction);
    }
}
