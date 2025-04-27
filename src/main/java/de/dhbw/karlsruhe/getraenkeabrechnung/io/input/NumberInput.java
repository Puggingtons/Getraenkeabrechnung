package de.dhbw.karlsruhe.getraenkeabrechnung.io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class NumberInput extends Input<Integer> {

    private final int minValue;
    private final int maxValue;

    public NumberInput(String prompt) {
        super(prompt);
        this.minValue = 1;
        this.maxValue = 99;
    }

    public NumberInput(String prompt, int minValue, int maxValue) {
        super(prompt);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    Result<Integer> getResult(String input) {
        try {
            Integer res = Integer.valueOf(input);
            if (res <= minValue || res >= maxValue) {
                return Result.none();
            }
            return Result.some(res);
        } catch (NumberFormatException e) {
            return Result.none();
        }
    }
}
