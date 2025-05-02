package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import java.util.HashMap;
import java.util.Map;

public class MenuInteraction extends Interaction<Void> {

    private final Map<String, Interaction<?>> interactions;
    private final SelectInteraction selectInteraction;

    public MenuInteraction() {
        interactions = new HashMap<>();
        selectInteraction = new SelectInteraction();
        selectInteraction.onSuccess(this::onSelect);
    }

    @Override
    String usage() {
        return selectInteraction.usage();
    }

    @Override
    protected void execute() {
        explain();
        selectInteraction.run();
    }

    public void addInteraction(String key, String description, Interaction<?> interaction) {
        interactions.put(key, interaction);
        selectInteraction.addOption(key, description);
    }

    private void onSelect(String selection) {
        Interaction<?> interaction = interactions.get(selection);

        if (interaction == null) {
            System.out.println("unknown command: " + selection);
            return;
        }

        interaction.explain();
        interaction.run();
        System.out.println();
    }
}
