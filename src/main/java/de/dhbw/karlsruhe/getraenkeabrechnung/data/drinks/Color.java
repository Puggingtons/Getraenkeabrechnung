package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class Color
{

    private ColorName colorName;
    private double colorPrice;

    public Color(ColorName colorName, double colorPrice)
    {
        this.colorName = colorName;
        this.colorPrice = colorPrice;
    }

    public ColorName getColorName()
    {
        return colorName;
    }

    public double getColorPrice()
    {
        return colorPrice;
    }

    public void setColorName(ColorName colorName)
    {
        this.colorName = colorName;
    }

    public void setColorPrice(double colorPrice)
    {
        this.colorPrice = colorPrice;
    }
}
