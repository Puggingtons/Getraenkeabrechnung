package de.dhbw.karlsruhe.getraenkeabrechnung;

public class Position {
    
    private DrinkCategory drinkCategory;
    private int amount;
    
    public Position(DrinkCategory drinkCategory, int amount) {
        this.drinkCategory = drinkCategory;
        this.amount = amount;
    }

    public DrinkCategory getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrink(DrinkCategory drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        // needs to be checked for negative (or null) values
        this.amount = amount;
    }
    

}
