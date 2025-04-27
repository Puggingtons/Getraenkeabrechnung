package de.dhbw.karlsruhe.getraenkeabrechnung;

public class DrinkCategory {

    private DrinkName drinkName;
    private ColorName colorName;

    public DrinkCategory(DrinkName drinkName, ColorName colorName) {
        this.drinkName = drinkName;
        this.colorName = colorName;
    }

    public DrinkName getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(DrinkName drinkName) {
        this.drinkName = drinkName;
    }

    public ColorName getColorName() {
        return colorName;
    }

    public void setColor(ColorName colorName) {
        this.colorName = colorName;
    }

}
