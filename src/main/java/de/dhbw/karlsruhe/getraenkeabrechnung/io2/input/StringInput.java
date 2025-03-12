package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

public class StringInput extends Input<String> {

    private final String prompt;

    public StringInput(String prompt) {
        super();
        this.prompt = prompt;
    }

    @Override
    public Result<String> prompt() {
        println(prompt);

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
