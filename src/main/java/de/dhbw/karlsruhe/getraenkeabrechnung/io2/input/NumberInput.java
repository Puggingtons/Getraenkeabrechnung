package de.dhbw.karlsruhe.getraenkeabrechnung.io2.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io2.input.result.Result;

public class NumberInput extends Input<Integer> {

//    todo: range? (min and max value)

    public NumberInput(String prompt) {
        super(prompt);
    }

    @Override
    public Result<Integer> getResult(String input) {
        try {
            Integer res = Integer.valueOf(input);
            return Result.some(res);
        } catch (NumberFormatException e) {
            return Result.none();
        }
    }
}
