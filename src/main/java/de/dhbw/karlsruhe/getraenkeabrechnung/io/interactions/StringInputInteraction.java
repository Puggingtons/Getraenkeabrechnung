package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class StringInputInteraction extends Interaction<String> {

    private final String message;
    private final String prompt;
    private final StringInput input;

    public StringInputInteraction(String msg) {
        this(msg, DEFAULT_PROMPT);
    }

    public StringInputInteraction(String msg, String prompt) {
        this.message = msg;
        this.prompt = prompt;

        input = new StringInput(prompt);
    }

    @Override
    String usage() {
        return message;
    }

    @Override
    protected void execute() {
        Result<String> result = input.prompt();

        if (result.isHelp()) {
            explain();
            return;
        }

        if (result.isNone()) {
            System.out.println("Invalid input!");
            failure();
            return;
        }

        String res = result.getValue();
        System.out.println("Your input: " + res);

        success(res);
    }
}
