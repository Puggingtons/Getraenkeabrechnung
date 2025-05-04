package de.dhbw.karlsruhe.getraenkeabrechnung.data.drinks;

public class CategoryName
{

    private final String colorNameString;

    public CategoryName(String colorName)
    {
        this.colorNameString = colorName;
    }

    @Override
    public String toString()
    {
        return this.colorNameString;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryName colorName = (CategoryName) o;
        return colorNameString.equals(colorName.colorNameString);
    }

}
