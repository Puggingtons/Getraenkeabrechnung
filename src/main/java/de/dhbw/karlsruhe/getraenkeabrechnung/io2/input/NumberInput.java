package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import java.util.Optional;

public class NumberInput extends Input<Integer> {

//    todo: range? (min and max value)

    private final String prompt;

    public NumberInput(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public Optional<Integer> prompt() {
        print(prompt);

        String in = readInput();

        if (in.isEmpty()) {
            return Optional.empty();
        }

        try {
            Integer res = Integer.valueOf(in);
            return Optional.of(res);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
