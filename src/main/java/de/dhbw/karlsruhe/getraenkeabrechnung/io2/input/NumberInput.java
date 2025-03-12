package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import java.util.Optional;

public class NumberInput extends Input<Integer> {

//    todo: range? (min and max value)

    private final String prompt;

    public NumberInput(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public Result<Integer> prompt() {
        print(prompt);

        String in = readInput();

        if (isHelp(in)) {
            return Result.help();
        }

        if (in.isEmpty()) {
            return Result.none();
        }

        try {
            Integer res = Integer.valueOf(in);
            return Result.some(res);
        } catch (NumberFormatException e) {
            return Result.none();
        }
    }
}
