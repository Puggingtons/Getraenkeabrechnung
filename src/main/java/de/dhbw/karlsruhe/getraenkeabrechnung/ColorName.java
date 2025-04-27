package de.dhbw.karlsruhe.getraenkeabrechnung;

public class ColorName {

    private final String colorNameString;

    public ColorName(String colorName) {
        this.colorNameString = colorName;
    }

    @Override
    public String toString() {
        return this.colorNameString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorName colorName = (ColorName) o;
        return colorNameString.equals(colorName.colorNameString);
    }

}
