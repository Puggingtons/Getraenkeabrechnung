package de.dhbw.karlsruhe.getraenkeabrechnung.io.input;

import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class FloatInput extends Input<Float>
{

    public FloatInput(String prompt)
    {
        super(prompt);
    }

    @Override
    Result<Float> getResult(String input)
    {
        try
        {
            Float res = Float.parseFloat(input);
            return Result.some(res);
        } catch (NumberFormatException e)
        {
            return Result.none();
        }
    }
}
