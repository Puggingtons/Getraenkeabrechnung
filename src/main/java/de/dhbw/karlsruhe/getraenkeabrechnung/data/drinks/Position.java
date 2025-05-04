package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class Position
{

    private DrinkOption drinkOption;
    private int amount;

    public Position(DrinkOption drinkOption, int amount)
    {
        this.drinkOption = drinkOption;
        this.amount = amount;
    }

    public DrinkOption getDrinkOption()
    {
        return drinkOption;
    }

    public void setDrink(DrinkOption drinkOption)
    {
        this.drinkOption = drinkOption;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        // needs to be checked for negative (or null) values
        this.amount = amount;
    }


}
