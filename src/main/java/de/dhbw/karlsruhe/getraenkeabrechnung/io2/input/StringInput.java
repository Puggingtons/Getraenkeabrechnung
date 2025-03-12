package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

public class StringInput extends Input {

    private final String prompt;

    public StringInput(String prompt) {
        super();
        this.prompt = prompt;
    }

    @Override
    public String prompt() {
        println(prompt);

        return readInput();
    }
}
