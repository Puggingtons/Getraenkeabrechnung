package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

public class StringInput extends Input<String> {

    public StringInput(String prompt) {
        super(prompt);
    }

    @Override
    Result<String> getResult(String input) {
        return Result.some(input);
    }
}
