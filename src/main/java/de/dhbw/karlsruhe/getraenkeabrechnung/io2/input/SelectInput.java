package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

public class SelectInput extends Input {

    private final String[] options;

    public SelectInput(String[] options) {
        super();
        this.options = options;
    }

    @Override
    public String prompt() {
        printOptions();
        return readInput();
    }

    private void printOptions() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            builder.append("[").append(i).append("] ").append(options[i]).append("\n");
        }
        print(builder.toString());
    }
}
