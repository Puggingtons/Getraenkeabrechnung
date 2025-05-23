package de.dhbw.karlsruhe.getraenkeabrechnung.io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class BooleanInput extends Input<Boolean>
{

    public BooleanInput(String prompt)
    {
        super(prompt);
    }

    @Override
    Result<Boolean> getResult(String input)
    {
        String normalizedInput = input.toLowerCase();
        return switch (normalizedInput)
        {
            case "y", "yes" -> Result.some(true);
            case "n", "no" -> Result.some(false);
            default -> Result.none();
        };
    }
}
