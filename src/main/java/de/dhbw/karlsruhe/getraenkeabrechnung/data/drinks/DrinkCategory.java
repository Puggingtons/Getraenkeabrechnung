package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class DrinkCategory
{

    private DrinkOption drinkOption;
    private CategoryName colorName;

    public DrinkCategory(DrinkOption drinkOption, CategoryName colorName) {
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

    public CategoryName getColorName()
    {
        return colorName;
    }

    public void setColor(CategoryName colorName)
    {
        this.colorName = colorName;
    }

}
