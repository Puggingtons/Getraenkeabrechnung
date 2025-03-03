package de.dhbw.karlsruhe.getraenkeabrechnung;

public class DrinkCategory {

    private String drinkName;
    private Color color;

    public DrinkCategory(String drinkName, Color color) {
        this.drinkName = drinkName;
        this.color = color;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice(Color color) {
        return color.getColorPrice();
    }

}
