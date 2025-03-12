package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

public class StringInput extends Input<String> {

    private final String prompt;

    public StringInput(String prompt) {
        super();
        this.prompt = prompt;
    }

    @Override
    public Result<String> prompt() {
        print(prompt);

        String res = readInput();

        if (isHelp(res)) {
            return Result.help();
        }

        // return an empty optional if the returned string is blank
        if (res.isBlank()) {
            return Result.none();
        }

        return Result.some(res);
    }
}
