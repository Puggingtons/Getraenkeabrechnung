package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class CategoryOption {

    private CategoryName colorName;
    private double colorPrice;

    public CategoryOption(CategoryName colorName, double colorPrice) {
        this.colorName = colorName;
        this.colorPrice = colorPrice;
    }

    public CategoryName getColorName() {
        return colorName;
    }

    public double getColorPrice() {
        return colorPrice;
    }

    public void setColorName(CategoryName colorName) {
        this.colorName = colorName;
    }

    public void setColorPrice(double colorPrice) {
        this.colorPrice = colorPrice;
    }
}
