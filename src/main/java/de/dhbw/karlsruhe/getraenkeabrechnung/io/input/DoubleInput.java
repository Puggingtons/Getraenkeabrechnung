package de.dhbw.karlsruhe.getraenkeabrechnung.io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class DoubleInput extends Input<Double> {

    public DoubleInput(String prompt) {
        super(prompt);
    }

    @Override
    Result<Double> getResult(String input) {
        try {
            return Result.some(Double.parseDouble(input));
        } catch (NumberFormatException e) {
            return Result.none();
        }
    }
    
}
