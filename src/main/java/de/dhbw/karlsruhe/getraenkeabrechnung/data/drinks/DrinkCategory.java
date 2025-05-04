package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class DrinkCategory
{

    private DrinkOption drinkOption;
    private ColorName colorName;

    public DrinkCategory(DrinkOption drinkOption, ColorName colorName)
    {
        this.drinkOption = drinkOption;
        this.colorName = colorName;
    }

    public DrinkOption getDrinkOption()
    {
        return drinkOption;
    }

    public void setDrinkOption(DrinkOption drinkOption)
    {
        this.drinkOption = drinkOption;
    }

    public ColorName getColorName()
    {
        return colorName;
    }

    public void setColor(ColorName colorName)
    {
        this.colorName = colorName;
    }

}
