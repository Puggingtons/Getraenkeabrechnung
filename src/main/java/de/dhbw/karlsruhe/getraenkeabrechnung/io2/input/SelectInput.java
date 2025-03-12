package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import java.util.Optional;

public class SelectInput extends Input<Integer> {

    private final String[] options;

    public SelectInput(String[] options) {
        super();
        this.options = options;
    }

    @Override
    public Optional<Integer> prompt() {
        printOptions();

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

    private void printOptions() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            builder.append("[").append(i).append("] ").append(options[i]).append("\n");
        }
        print(builder.toString());
    }
}
