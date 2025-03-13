package de.dhbw.karlsruhe.getraenkeabrechnung.io2.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

public class StringInputInteraction implements Interaction<String> {

    private final String message;
    private final String prompt;

    public StringInputInteraction(String msg) {
        this.message = msg;
        this.prompt = DEFAULT_PROMPT;
    }

    public StringInputInteraction(String msg, String prompt) {
        this.message = msg;
        this.prompt = prompt;
    }

    @Override
    public void explain() {
        System.out.println(message);
    }

    @Override
    public String run() {
        StringInput input = new StringInput(prompt);

        while (true) {
            Result<String> result = input.prompt();

            if (result.isHelp()) {
                explain();
                continue;
            }

            if (result.isNone()) {
                System.out.println("Invalid input!");
                continue;
            }

            System.out.println("Your input: " + result.getValue());
            return result.getValue();
        }
    }
}
