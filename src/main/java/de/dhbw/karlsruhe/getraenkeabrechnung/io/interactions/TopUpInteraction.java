package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.numbers.Money;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.DoubleInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class TopUpInteraction extends Interaction<Money>
{

    private final DoubleInput moneyInput;

    public TopUpInteraction()
    {
        moneyInput = new DoubleInput("Top up amount: ");
    }

    @Override
    String usage()
    {
        return "Top up your account.";
    }

    @Override
    protected void execute()
    {
        Double result = getValidInput(moneyInput);

        success(new Money(String.valueOf(result)));
    }

    private double getValidInput(DoubleInput input)
    {
        while (true)
        {
            Result<Double> result = input.prompt();

            if (result.isHelp())
            {
                explain();
                continue;
            }

            if (result.isNone())
            {
                System.out.println("Invalid input!");
                continue;
            }

            return result.getValue();
        }
    }
}
