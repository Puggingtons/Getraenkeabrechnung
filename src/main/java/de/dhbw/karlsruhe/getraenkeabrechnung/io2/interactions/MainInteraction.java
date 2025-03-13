package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import java.util.HashMap;
import java.util.Map;

public class MainInteraction implements Interaction<Void> {

    private final Map<String, Interaction> interactions;
    private SelectInteraction selectInteraction;

    public MainInteraction() {
        this.interactions = new HashMap<>();
        this.selectInteraction = new SelectInteraction();

        addInteraction("exit", "exits the program", null);
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
}
