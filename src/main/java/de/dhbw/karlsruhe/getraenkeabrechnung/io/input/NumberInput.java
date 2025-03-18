package de.dhbw.karlsruhe.getraenkeabrechnung.io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class NumberInput extends Input<Integer> {

//    todo: range? (min and max value)

    public NumberInput(String prompt) {
        super(prompt);
    }

    @Override
    Result<Integer> getResult(String input) {
        try {
            Integer res = Integer.valueOf(input);
            return Result.some(res);
        } catch (NumberFormatException e) {
            return Result.none();
        }
    }
}
