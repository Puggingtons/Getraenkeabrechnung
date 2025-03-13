package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

import java.util.HashMap;
import java.util.Map;

public class SelectInteraction implements Interaction<String> {

    private final Map<String, String> options;
    private final String prompt;

    public SelectInteraction() {
        this(new HashMap<>());
    }

    public SelectInteraction(Map<String, String> options) {
        this(options, DEFAULT_PROMPT);
    }

    public SelectInteraction(Map<String, String> options, String prompt) {
        this.options = options;
        this.prompt = prompt;
    }

    // todo: move this functionality to a new class
    public void pushOption(String option) {
        this.options.put(String.valueOf(this.options.size()), option);
    }

    public void addOption(String key, String option) {
        this.options.put(key, option);
    }

    public void explain() {
        options.forEach((k, v) -> System.out.println("[ " + k + " ] " + v));
    }

    public String run() {
        StringInput input = new StringInput(prompt);

        while (true) {
            Result<String> result = input.prompt();

            if (result.isNone()) {
                System.out.println("Invalid input!");
                return ""; // todo
            }

            if (result.isHelp()) {
                explain();
                continue;
            }

            String key = result.getValue();

            if (!options.containsKey(key)) {
                System.out.println("Unknown option: " + key);
                continue;
            }

            return key;
        }


    }
}