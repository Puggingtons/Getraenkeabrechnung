package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.DrinkOption;

public class PurchaseInteraction extends SelectInteraction
{

    private final ThirstyCalc thirstyCalc;

    public PurchaseInteraction(ThirstyCalc thirstyCalc)
    {
        this.thirstyCalc = thirstyCalc;
    }

    @Override
    String usage()
    {
        return "Select a drink by index from the following list:\n" + super.usage();
    }

    @Override
    protected void execute()
    {
        updateOptions();
        this.explain();
        super.execute();
    }

    private void updateOptions()
    {
        clearOptions();
        DrinkOption[] drinkOptions = thirstyCalc.getDrinkDatabase().getDrinkOptions();

        for (DrinkOption d : drinkOptions)
        {
            this.pushOption(String.format("%s - %s", d.getColorName(), d.getDrinkName()));
        }
    }
}
