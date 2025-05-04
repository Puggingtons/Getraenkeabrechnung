package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.CategoryName;
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

        drinkNameInput = new StringInput("Drink name: ");
        colorNameInput = new StringInput("Color of drinks category: ");
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

        DrinkOption drinkOption = new DrinkOption(new DrinkName(drinkName), new CategoryName(colorName));

        if (thirstyCalc.drinkOptionExists(drinkOption))
        {
            // If the drink option already exists, we don't need to add it again
            System.out.println("Drink with that name already exists in that category!");
            failure();
            return;
        }


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

