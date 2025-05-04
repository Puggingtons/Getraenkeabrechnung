package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.ColorName;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkOption;
import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkName;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.StringInput;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.input.result.Result;

public class CreateDrinkOptionInteraction extends Interaction<DrinkOption>
{

    private final ThirstyCalc thirstyCalc;

    private final StringInput drinkNameInput;
    private final StringInput colorNameInput;

    public CreateDrinkOptionInteraction(ThirstyCalc thirstyCalc)
    {

        this.thirstyCalc = thirstyCalc;

        drinkNameInput = new StringInput("Drinkname: ");
        colorNameInput = new StringInput("Drinkcolor: ");
    }

    @Override
    String usage()
    {
        return "Creates a new drink option.";
    }

    @Override
    protected void execute()
    {
        String drinkName = getValidInput(drinkNameInput);
        String colorName = getValidInput(colorNameInput);

        if (thirstyCalc.drinkOptionExists(new DrinkName(drinkName)))
        {
            // If the drink option already exists, we don't need to add it again
            System.out.println("Drink already exists!");
            failure();
            return;
        }

        DrinkOption drinkOption = new DrinkOption(new DrinkName(drinkName), new ColorName(colorName));

        success(drinkOption);
    }

    private String getValidInput(StringInput input)
    {
        while (true)
        {
            Result<String> result = input.prompt();

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

