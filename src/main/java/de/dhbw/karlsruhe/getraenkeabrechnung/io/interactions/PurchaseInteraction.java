package de.dhbw.karlsruhe.getraenkeabrechnung.io.interactions;

import de.dhbw.karlsruhe.getraenkeabrechnung.ThirstyCalc;
import de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks.Position;

public class PurchaseInteraction extends Interaction<Position>
{

    public CreatePurchaseInteraction(ThirstyCalc thirstyCalc)
    {
        this.thirstyCalc = thirstyCalc;

        positionInput = new StringInput("Position name: ");
    }

    @Override
    String usage()
    {
        return "Purchase a drink.";
    }

    @Override
    protected void execute()
    {
        String position
    }


}
