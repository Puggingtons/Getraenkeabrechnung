package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class DrinkOption
{

    private DrinkName drinkName;
    private ColorName colorName;

    public DrinkOption(DrinkName drinkName, ColorName colorName)
    {
        this.drinkName = drinkName;
        this.colorName = colorName;
    }

    public DrinkName getDrinkName()
    {
        return drinkName;
    }

    public void setDrinkName(DrinkName drinkName)
    {
        this.drinkName = drinkName;
    }

    public ColorName getColorName()
    {
        return colorName;
    }

    public void setColorName(ColorName colorName)
    {
        this.colorName = colorName;
    }
}
