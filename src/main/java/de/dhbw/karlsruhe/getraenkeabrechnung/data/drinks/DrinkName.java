package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class DrinkName
{

    private final String drinkNameString;

    public DrinkName(String drinkName)
    {
        this.drinkNameString = drinkName;
    }

    @Override
    public String toString()
    {
        return this.drinkNameString;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkName drinkName = (DrinkName) o;
        return drinkNameString.equals(drinkName.drinkNameString);
    }
}
