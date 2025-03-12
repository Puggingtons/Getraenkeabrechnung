package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import java.util.Optional;

public class StringInput extends Input<String> {

    private final String prompt;

    public StringInput(String prompt) {
        super();
        this.prompt = prompt;
    }

    @Override
    public Optional<String> prompt() {
        println(prompt);

        String res = readInput();

// return an empty optional if the returned string is blank
        if (res.isBlank()) {
            return Optional.empty();
        }

        return Optional.of(res);
    }
}
