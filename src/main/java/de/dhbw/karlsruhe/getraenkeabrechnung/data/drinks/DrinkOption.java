package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class DrinkOption {
        
    private DrinkName drinkName;
    private CategoryName colorName;

    public DrinkOption(DrinkName drinkName, CategoryName colorName) {
        this.drinkName = drinkName;
        this.colorName = colorName;
    }

    public DrinkName getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(DrinkName drinkName) {
        this.drinkName = drinkName;
    }

    public CategoryName getColorName() {
        return colorName;
    }

    public void setColorName(CategoryName colorName) {
        this.colorName = colorName;
    }
}
