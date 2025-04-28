package de.dhbw.karlsruhe.getraenkeabrechnung.data;

import de.dhbw.karlsruhe.getraenkeabrechnung.DrinkOption; // Adjust the package path if necessary
import java.util.List;

public class DrinkDatabase {

    private Savable<List<DrinkOption>> drinkOptionList;


    public void createNewDrinkOption(DrinkOption drinkOption) {
        // Implementation for creating a new drink option
        System.out.println("New drink option created for category: " + drinkOption);
    }
}