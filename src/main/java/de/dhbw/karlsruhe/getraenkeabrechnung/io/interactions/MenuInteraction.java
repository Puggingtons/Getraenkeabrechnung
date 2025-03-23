package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import java.util.HashMap;
import java.util.Map;

public class MenuInteraction extends Interaction<Void> {

    private final Map<String, Interaction<?>> interactions;
    private final SelectInteraction selectInteraction;

    public MenuInteraction() {
        interactions = new HashMap<>();
        selectInteraction = new SelectInteraction();
    }

    @Override
    public void explain() {
        selectInteraction.explain();
    }

    @Override
    public Void run() {
        while (true) {
            explain();
            String selection = selectInteraction.run();

            if (selection.equals("exit")) {
                // https://stackoverflow.com/questions/5568409/java-generics-void-void-types
                return null;
            }

            Interaction<?> interaction = interactions.get(selection);

            if (interaction == null) {
                System.out.println("unknown command: " + selection);
                continue;
            }

            interaction.explain();
            interaction.run();
            System.out.println();
        }
    }

    public void addInteraction(String key, String description, Interaction<?> interaction) {
        interactions.put(key, interaction);
        selectInteraction.addOption(key, description);
    }
}
